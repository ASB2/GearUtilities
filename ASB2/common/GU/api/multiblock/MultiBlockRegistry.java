package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;

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

    public boolean registerMultiBlock(String modName, String multiBlockName, Class<? extends IMultiBlock> multiBlock) {

        Set<String> multiList = multiBlockRegisterer.get(modName);

        if (!registeredMultiBlocks.containsKey(multiBlockName)) {

            registeredMultiBlocks.put(multiBlockName, multiBlock);

            if (multiList == null) {

                multiList = new HashSet<String>();
                multiBlockRegisterer.put(modName, multiList);
            }

            if (!multiList.contains(multiBlockName)) {

                multiList.add(multiBlockName);
                return true;
            }
        }

        // If it gets to this point it didnt work.
        registeredMultiBlocks.remove(multiBlockName);

        if (multiList != null) {

            multiList.remove(multiBlockName);
        }
        multiBlockRegisterer.remove(modName);

        return false;
    }

    public Class<? extends IMultiBlock> getMultiBlockFromName(String name) {

        return registeredMultiBlocks.get(name);
    }

    public String getNameFromMultiBlock(Class<? extends IMultiBlock> multiBlock) {

        return registeredMultiBlocks.inverse().get(multiBlock);
    }

    public Set<String> getModNameFromMultiBlock(Class<? extends IMultiBlock> multiBlock) {

        return multiBlockRegisterer.get(registeredMultiBlocks.inverse().get(multiBlock));
    }

    public static MultiBlockRegistry getInstance() {

        return instance;
    }
}
