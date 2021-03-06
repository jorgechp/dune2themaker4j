package com.fundynamic.d2tm.game.entities.entitiesdata.ini;

import com.fundynamic.d2tm.Game;
import com.fundynamic.d2tm.game.entities.entitiesdata.EntitiesData;
import com.fundynamic.d2tm.game.entities.entitybuilders.EntityBuilderType;
import com.fundynamic.d2tm.utils.StringUtils;
import org.ini4j.Profile;

import static com.fundynamic.d2tm.game.entities.entitiesdata.EntitiesDataReader.*;

/**
 * Object representation of a STRUCTURE entry in the INI file.
 */
public class IniDataStructure {

    public String image;
    public int width;
    public int height;
    public int sight;
    public int hitpoints;
    public String explosion;
    public String buildIcon;
    public String entityBuilderType;

    public float buildTimeInSeconds;
    public int buildRangeInTiles;
    public int buildCost;
    public String buildList;

    public boolean refinery;
    public String onPlacementSpawn;
    public int powerConsumption;
    public int powerProduction;

    public float minimumPowerProduction;

    public IniDataStructure() {
    }

    public IniDataStructure(Profile.Section struct) {
        this.minimumPowerProduction = struct.get(INI_KEYWORD_ON_MINIMUM_POWER_PRODUCTION, Float.class, 0.25f);
        this.onPlacementSpawn = struct.get(INI_KEYWORD_ON_PLACEMENT_SPAWN, String.class, EntitiesData.UNKNOWN);
        this.refinery = struct.get(INI_KEYWORD_REFINERY, Boolean.class, false);
        this.image = struct.get(INI_KEYWORD_IMAGE, String.class, null);
        this.width = struct.get(INI_KEYWORD_WIDTH, Integer.class);
        this.height = struct.get(INI_KEYWORD_HEIGHT, Integer.class);
        this.sight = struct.get(INI_KEYWORD_SIGHT, Integer.class);
        this.powerConsumption = struct.get(INI_KEYWORD_POWER_CONSUMPTION, Integer.class, 0);
        this.powerProduction = struct.get(INI_KEYWORD_POWER_PRODUCTION, Integer.class, 0);
        this.hitpoints = struct.get(INI_KEYWORD_HIT_POINTS, Integer.class, 0);
        this.explosion = struct.get(INI_KEYWORD_EXPLOSION, String.class, EntitiesData.UNKNOWN);
        this.buildIcon = struct.get(INI_KEYWORD_BUILD_ICON, String.class, null);
        this.entityBuilderType = struct.get(INI_KEYWORD_BUILDS, String.class, "");
        if (Game.RECORDING_VIDEO) {
            this.buildTimeInSeconds = 1;
        } else {
            this.buildTimeInSeconds = struct.get(INI_KEYWORD_BUILD_TIME, Float.class, 0F);
        }
        this.buildList = struct.get(INI_KEYWORD_BUILD_LIST, String.class, "");
        this.buildRangeInTiles = struct.get(INI_KEYWORD_BUILD_RANGE, Integer.class, 0);
        this.buildCost = struct.get(INI_KEYWORD_BUILD_COST, Integer.class, 0);
    }

    public EntityBuilderType getEntityBuilderType() {
        try {
            if (StringUtils.isEmpty(entityBuilderType)) {
                return EntityBuilderType.NONE;
            }
            return EntityBuilderType.valueOf(entityBuilderType);
        } catch (Exception e) {
            System.err.println("Unable to convert 'builds' property with value [" + entityBuilderType + "] into enum EntityBuilderType. Valid values are " + EntityBuilderType.getValues());
            return EntityBuilderType.NONE;
        }
    }
}
