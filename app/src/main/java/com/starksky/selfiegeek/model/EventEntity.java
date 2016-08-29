package com.starksky.selfiegeek.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

/**
 * Created by NTQK0716 on 2016-08-29.
 */
public class EventEntity extends GenericJson {

    @Key("appKey")
    private String appKey;
    @Key("userID")
    private String userID; // Kinvey metadata, OPTIONAL
  /*  @Key("_acl")
    private KinveyMetaData.AccessControlList acl; //Kinvey access control, OPTIONAL*/
    public EventEntity(){}  //GenericJson classes must have a public empty constructor

}
