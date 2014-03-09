package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import GU.GearUtilities;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MultiBlockRegistry {

    private static MultiBlockRegistry instance = new MultiBlockRegistry();

    /**
     * Maps Provided Name To A MultiBlock Class
     */
    private BiMap<String, Class<? extends IMultiBlock>> registeredMultiBlocks = HashBiMap.create();

    /**
     * Maps Provided Mod Name To A MultiBlock Name
     */
    private BiMap<String, Set<String>> multiBlockRegisterer = HashBiMap.create();

    /**
     * Maps Provided MultiBlock Class To A Mod Name
     */
    private BiMap<Class<? extends IMultiBlock>, String> multiBlockModNames = HashBiMap.create();

    /**
     * Maps Provided Mod To A MultiBlockHandler Name
     */
    private BiMap<String, IModMultiBlockHandler> multiBlockHandlers = HashBiMap.create();

    public boolean registerMultiBlock(String modName, String multiBlockName, IModMultiBlockHandler multiBlockHandler, Class<? extends IMultiBlock> multiBlock) {

        if (!multiBlockHandlers.containsKey(modName)) {

            multiBlockHandlers.put(modName, multiBlockHandler);
        } else {
            GearUtilities.logger.log(Level.SEVERE, modName + " registered a multiblock handler that is already in the HashMap");
            return false;
        }

        Set<String> multiList = multiBlockRegisterer.get(modName);

        if (!registeredMultiBlocks.containsKey(multiBlockName)) {

            registeredMultiBlocks.put(multiBlockName, multiBlock);

            if (!multiBlockModNames.containsKey(multiBlock) && !multiBlockModNames.containsValue(modName)) {
                multiBlockModNames.put(multiBlock, modName);
            }
            if (multiList == null) {

                multiList = new HashSet<String>();
                multiBlockRegisterer.put(modName, multiList);
            }

            if (!multiList.contains(multiBlockName)) {

                multiList.add(multiBlockName);
                return true;
            }
        } else {

            GearUtilities.logger.log(Level.SEVERE, modName + " registered a multiblock that is already in the HashMap");
        }

        // If it gets to this point it didnt work.
        registeredMultiBlocks.remove(multiBlockName);

        if (multiList != null) {

            multiList.remove(multiBlockName);
        }
        multiBlockRegisterer.remove(modName);
        multiBlockModNames.remove(multiBlock);
        return false;
    }

    public Class<? extends IMultiBlock> getMultiBlockClassFromMultiBlockName(String name) {

        return registeredMultiBlocks.get(name);
    }

    public String getMultiBlockNameFromMultiBlockClass(Class<? extends IMultiBlock> multiBlock) {

        return registeredMultiBlocks.inverse().get(multiBlock);
    }

    public String getModNameFromMultiBlockClass(Class<? extends IMultiBlock> multiBlock) {

        return multiBlockModNames.get(multiBlock);
    }

    public IModMultiBlockHandler getMultiBlockHandlerNameFromMultiBlockName(String name) {

        return multiBlockHandlers.get(multiBlockModNames.get(this.getMultiBlockClassFromMultiBlockName(name)));
    }

    public IModMultiBlockHandler getMultiBlockHandlerNameFromModName(String name) {

        return multiBlockHandlers.get(name);
    }

    public String getModNameFromMultiBlock(String multiBlock) {

        return multiBlockModNames.get(multiBlock);
    }

    public IMultiBlock getMultiBlockInstanceFromMutliBlockName(String multiBlockName) {

        return getMultiBlockHandlerNameFromMultiBlockName(multiBlockName).getMultiBlockInstance(multiBlockName);
    }

    public static MultiBlockRegistry getInstance() {

        return instance;
    }
}
