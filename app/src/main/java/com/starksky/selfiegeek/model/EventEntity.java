package com.starksky.selfiegeek.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

/**
 * Created by NTQK0716 on 2016-08-29.
 */
public class EventEntity extends GenericJson {

    @Key("Download Link")
    private String downloadLink;
    @Key("_id")
    private String _id;
    @Key("_kmd")
    private KinveyMetaData meta; // Kinvey metadata, OPTIONAL
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl; //Kinvey access control, OPTIONAL
    @Key("_filename")
    private String _filename;
    @Key("_public")
    private boolean _public;
    @Key("mimeType")
    private String mimeType;
    @Key("size")
    private String size;


    public EventEntity(){}  //GenericJson classes must have a public empty constructor

}
