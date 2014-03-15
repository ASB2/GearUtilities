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

        Set<String> multiList = multiBlockRegisterer.get(modName);

        if (!multiBlockHandlers.containsKey(modName) && !registeredMultiBlocks.containsKey(multiBlockName) && !multiBlockModNames.containsKey(multiBlock) && !multiBlockModNames.containsValue(modName)) {

            multiBlockHandlers.put(modName, multiBlockHandler);

            registeredMultiBlocks.put(multiBlockName, multiBlock);

            multiBlockModNames.put(multiBlock, modName);

            if (multiList == null) {

                multiList = new HashSet<String>();
                multiBlockRegisterer.put(modName, multiList);

                multiList.add(multiBlockName);
            }
            return true;

        } else {
            GearUtilities.logger.log(Level.SEVERE, modName + " registered a multiblock handler that is already in the HashMap");
            return false;
        }
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

    public IModMultiBlockHandler getMultiBlockHandlerNameFromMultiBlockName(String multiName) {

        return multiBlockHandlers.get(multiBlockModNames.get(this.getMultiBlockClassFromMultiBlockName(multiName)));
    }

    public IModMultiBlockHandler getMultiBlockHandlerNameFromModName(String name) {

        return multiBlockHandlers.get(name);
    }

    public String getModNameFromMultiBlock(String multiBlock) {

        return multiBlockModNames.get(multiBlock);
    }

    public IMultiBlock getMultiBlockInstanceFromMutliBlockName(String multiBlockName) {
        IModMultiBlockHandler instance = getMultiBlockHandlerNameFromMultiBlockName(multiBlockName);
        if (instance == null) {
            GearUtilities.logger.log(Level.SEVERE, this.getModNameFromMultiBlock(multiBlockName) + " registered a multiblock handler that is null");
            return null;
        }
        return instance.getMultiBlockInstance(multiBlockName);
    }

    public static MultiBlockRegistry getInstance() {

        return instance;
    }
}
