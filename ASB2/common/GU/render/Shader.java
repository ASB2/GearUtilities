package GU.render;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Matrix4d;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import GU.GearUtilities;

/**
 * 
 * @author ASB2
 * 
 */
public class Shader {
    
    private String shaderName = "Not Named";
    private boolean compiled = false;
    private int shaderPointer;
    private Map<String, Integer> uniforms;
    private List<ShaderProgram> subPrograms;
    
    public Shader() {
        
        shaderPointer = -1;
        uniforms = new HashMap<String, Integer>();
        subPrograms = new ArrayList<ShaderProgram>();
    }
    
    /**
     * @return the shaderName
     */
    public String getShaderName() {
        
        return shaderName;
    }
    
    /**
     * @param shaderName
     *            the shaderName to set
     */
    public Shader setShaderName(String shaderName) {
        
        this.shaderName = shaderName;
        return this;
    }
    
    public void bind() {
        
        if (compiled) {
            
            GL20.glUseProgram(shaderPointer);
        } else {
            
            GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": Cannot Bind Uncompiled Shader"));
        }
    }
    
    public void unBind() {
        
        GL20.glUseProgram(0);
    }
    
    public boolean addSubProgram(String text, int type) {
        
        if (!compiled) {
            
            int subProgramID = GL20.glCreateShader(type);
            
            GL20.glShaderSource(subProgramID, text);
            GL20.glCompileShader(subProgramID);
            
            if (GL20.glGetShaderi(subProgramID, GL20.GL_COMPILE_STATUS) == 0) {
                
                GearUtilities.log(GL20.glGetProgramInfoLog(subProgramID, 1024));
                GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": Cannot Add Sub Program"));
                return false;
            }
            GL20.glAttachShader(this.getShaderPointer(), subProgramID);
            this.subPrograms.add(new ShaderProgram(type).setID(subProgramID).setProgram(text));
            return true;
        } else {
            
            GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": Cannot add program to Uncompiled Shader"));
        }
        return false;
    }
    
    public boolean addSubProgram(String text, EnumShaderProgramType type) {
        
        return addSubProgram(text, type.getTypeValue());
    }
    
    public boolean compileShader() {
        
        if (!compiled) {
            
            if (!subPrograms.isEmpty()) {
                
                GL20.glLinkProgram(this.getShaderPointer());
                
                if (GL20.glGetProgrami(this.getShaderPointer(), GL20.GL_LINK_STATUS) == 0) {
                    
                    GearUtilities.log(GL20.glGetProgramInfoLog(this.getShaderPointer(), 1024));
                    return false;
                }
                
                GL20.glValidateProgram(this.getShaderPointer());
                
                if (GL20.glGetProgrami(this.getShaderPointer(), GL20.GL_VALIDATE_STATUS) == 0) {
                    
                    GearUtilities.log(GL20.glGetProgramInfoLog(this.getShaderPointer(), 1024));
                    return false;
                }
                compiled = true;
                return true;
            } else {
                
                GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": No SubPrograms Installed"));
            }
        } else {
            
            GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": Cannot Recompile Shader"));
        }
        return false;
    }
    
    public int getShaderPointer() {
        
        if (!compiled && shaderPointer == -1) {
            
            shaderPointer = GL20.glCreateProgram();
        }
        return shaderPointer;
    }
    
    public boolean addUniform(String uniform) {
        
        int uniformLocation = GL20.glGetUniformLocation(this.getShaderPointer(), uniform);
        
        if (uniformLocation == -1) {
            
            GearUtilities.log("Shader Error: ".concat(getShaderName()).concat(": Could not find uniform: ".concat(uniform)));
            return false;
        }
        uniforms.put(uniform, uniformLocation);
        return true;
    }
    
    public void setUniform(String uniformName, int value) {
        
        GL20.glUniform1i(uniforms.get(uniformName), value);
    }
    
    public void setUniform(String uniformName, float value) {
        
        GL20.glUniform1f(uniforms.get(uniformName), value);
    }
    
    public void setUniform(String uniformName, double value) {
        
        this.setUniform(uniformName, (float) value);
    }
    
    public void setUniform(String uniformName, Vector3d value) {
        
        GL20.glUniform3f(uniforms.get(uniformName), (float) value.getX(), (float) value.getY(), (float) value.getZ());
    }
    
    public void setUniform(String uniformName, Matrix4d value) {
        
        GL20.glUniformMatrix4(uniforms.get(uniformName), true, (FloatBuffer) value.toFloatBuffer().flip());
    }
    
    public boolean addVertexShader(String text) {
        
        return addSubProgram(text, GL20.GL_VERTEX_SHADER);
    }
    
    public boolean addFragmentShader(String text) {
        
        return addSubProgram(text, GL20.GL_FRAGMENT_SHADER);
    }
    
    public boolean addGeometryShader(String text) {
        
        return addSubProgram(text, GL32.GL_GEOMETRY_SHADER);
    }
    
    public static String loadShaderText(File file) {
        
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            
            shaderReader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = shaderReader.readLine()) != null) {
                
                shaderSource.append(line).append("\n");
            }
            
            shaderReader.close();
        } catch (IOException e) {
            
            e.printStackTrace();
            GearUtilities.log("Cannot Load Shader: ".concat(file.toString()));
            return "";
        }
        
        return shaderSource.toString();
    }
    
    public static String loadShaderText(InputStream file) {
        
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            
            shaderReader = new BufferedReader(new InputStreamReader(file));
            String line;
            
            while ((line = shaderReader.readLine()) != null) {
                
                shaderSource.append(line).append("\n");
            }
            
            shaderReader.close();
        } catch (IOException e) {
            
            e.printStackTrace();
            GearUtilities.log("Cannot Load Shader: ".concat(file.toString()));
            return "";
        }
        
        return shaderSource.toString();
    }
    
    public class ShaderProgram {
        
        EnumShaderProgramType type;
        String text;
        int subProgramID = -1;
        
        public ShaderProgram(EnumShaderProgramType type) {
            
            this.type = type;
        }
        
        public ShaderProgram(int type) {
            this(EnumShaderProgramType.translateTypeToEnum(type));
        }
        
        public ShaderProgram setProgram(String text) {
            
            this.text = text;
            return this;
        }
        
        public String getText() {
            
            return text;
        }
        
        public ShaderProgram setID(int id) {
            
            subProgramID = id;
            return this;
        }
        
        public int getID() {
            
            return subProgramID;
        }
    }
    
    public enum EnumShaderProgramType {
        
        VERTEX(GL_VERTEX_SHADER), FRAGMENT(GL_FRAGMENT_SHADER), GEOMETRY(GL_GEOMETRY_SHADER);
        
        private int type;
        
        private EnumShaderProgramType(int type) {
            
            this.type = type;
        }
        
        public int getTypeValue() {
            
            return type;
        }
        
        public static EnumShaderProgramType translateTypeToEnum(int type) {
            
            switch (type) {
            
                case GL_VERTEX_SHADER:
                    return VERTEX;
                case GL_FRAGMENT_SHADER:
                    return FRAGMENT;
                case GL_GEOMETRY_SHADER:
                    return GEOMETRY;
                default: {
                    return null;
                }
            }
        }
    }
}
