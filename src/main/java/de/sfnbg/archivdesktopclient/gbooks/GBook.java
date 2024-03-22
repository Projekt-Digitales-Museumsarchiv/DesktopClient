package de.sfnbg.archivdesktopclient.gbooks;

import lombok.Data;

@Data
public class GBook {
    String id;
    String selfLink;
    GVolumeInfo volumeInfo;
}
