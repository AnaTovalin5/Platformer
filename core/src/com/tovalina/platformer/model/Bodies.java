package com.tovalina.platformer.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tovalina.platformer.controller.LevelController;


public class Bodies {
    public static void createBody(MapObject mapObject) {
        String bodyType = mapObject.getProperties().get("type").toString();

        if (bodyType.equalsIgnoreCase("solid")) {
            RectangleMapObject rectangleObject = (RectangleMapObject)mapObject;
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.StaticBody;
            bodyDefinition.position.set(rectangleObject.getRectangle().x * LevelController.UNIT_SCALE, rectangleObject.getRectangle().y * LevelController.UNIT_SCALE);

            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
            PolygonShape rectangleShape = new PolygonShape();
            rectangleShape.setAsBox(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f, rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f, new Vector2(rectangleObject.getRectangle().width * LevelController.UNIT_SCALE / 2f, rectangleObject.getRectangle().height * LevelController.UNIT_SCALE / 2f), 0f);

            FixtureDef fixtureDefinition = new FixtureDef();
            fixtureDefinition.shape = rectangleShape;

            physicsBody.createFixture(fixtureDefinition);
            rectangleShape.dispose();
        }else if (bodyType.equalsIgnoreCase("slope")) {
            PolygonMapObject polygonObject = (PolygonMapObject)mapObject;
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.StaticBody;
            bodyDefinition.position.set(polygonObject.getPolygon().getX() * LevelController.UNIT_SCALE, polygonObject.getPolygon().getY() * LevelController.UNIT_SCALE);

            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
            PolygonShape polygonShape = new PolygonShape();
            float[] transformedVertices = new float[polygonObject.getPolygon().getVertices().length];

            for (int index = 0; index < transformedVertices.length; index++) {  //transforms each vertex to UNIT_SCALE size
                transformedVertices[index] = polygonObject.getPolygon().getVertices()[index] * LevelController.UNIT_SCALE;
            }

            polygonShape.set(transformedVertices);  //

            FixtureDef fixtureDefinition = new FixtureDef();
            fixtureDefinition.shape = polygonShape;

            physicsBody.createFixture(fixtureDefinition);
            polygonShape.dispose();
        } else if (bodyType.equalsIgnoreCase("ground")) {
            PolylineMapObject polylineObject = (PolylineMapObject) mapObject;
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.StaticBody;

            bodyDefinition.position.set(polylineObject.getPolyline().getX() * LevelController.UNIT_SCALE, polylineObject.getPolyline().getY() * LevelController.UNIT_SCALE);

            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
            ChainShape chainShape = new ChainShape();
            float[] transformedVertices = new float[polylineObject.getPolyline().getVertices().length];

            for (int index = 0; index < transformedVertices.length; index++) {  //transforms each vertex to UNIT_SCALE size
                transformedVertices[index] = polylineObject.getPolyline().getVertices()[index] * LevelController.UNIT_SCALE;
            }

            chainShape.createChain(transformedVertices);

            FixtureDef fixtureDefiniton= new FixtureDef();
            fixtureDefiniton.shape= chainShape;
            fixtureDefiniton.friction= 1f;

            physicsBody.createFixture(fixtureDefiniton);
            chainShape.dispose();
        } else if (bodyType.equalsIgnoreCase("block")) {
            RectangleMapObject rectangleObject = (RectangleMapObject)mapObject;
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.DynamicBody;
            bodyDefinition.position.set(rectangleObject.getRectangle().x * LevelController.UNIT_SCALE, rectangleObject.getRectangle().y * LevelController.UNIT_SCALE);
            Block block = new Block(bodyDefinition.position, 70, 70, "img/background-tiles.png");
            LevelController.worldSprites.add(block);
            Body physicsBody = LevelController.gameWorld.createBody(bodyDefinition);
            physicsBody.setUserData(block);

            PolygonShape rectangleShape = new PolygonShape();
            rectangleShape.setAsBox(block.width/2f, block.height/2f, new Vector2(block.width/2f, block.height/2f), 0f);

            FixtureDef fixtureDefinition = new FixtureDef();
            fixtureDefinition.shape = rectangleShape;

            physicsBody.createFixture(fixtureDefinition);
            rectangleShape.dispose();
        }
    }
}
