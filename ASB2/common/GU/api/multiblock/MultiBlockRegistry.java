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

    public boolean registerMultiBlock(String modName, String multiBlockName, Class<? extends IMultiBlock> multiBlock) {

        try {
            multiBlock.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            GearUtilities.logger.log(Level.SEVERE, modName + " registered a multiblock that didnt pass test initilization");
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

    public Class<? extends IMultiBlock> getMultiBlockFromName(String name) {

        return registeredMultiBlocks.get(name);
    }

    public String getNameFromMultiBlock(Class<? extends IMultiBlock> multiBlock) {

        return registeredMultiBlocks.inverse().get(multiBlock);
    }

    public String getModNameFromMultiBlock(Class<? extends IMultiBlock> multiBlock) {

        return multiBlockModNames.get(multiBlock);
    }

    // public String getModNameFromMultiBlockName(String multiBlock) {
    //
    // return registeredMultiBlocks.inverse().get(key)
    // }
    public static MultiBlockRegistry getInstance() {

        return instance;
    }
}
