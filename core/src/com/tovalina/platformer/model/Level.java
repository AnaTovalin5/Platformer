package com.tovalina.platformer.model;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Level {
    public TiledMap map;

    public Level (String mapPath) {
        map = new TmxMapLoader().load(mapPath);      //load level map from my assets
    }

    public MapLayer getMapLayer(String layername) {
        return map.getLayers().get(layername);
    }

    public MapObjects getMapObjects(MapLayer mapLayer) {
        return mapLayer.getObjects();
    }

}
