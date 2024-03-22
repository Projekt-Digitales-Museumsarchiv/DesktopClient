package de.sfnbg.archivdesktopclient.gbooks;

import lombok.Data;

/*
Falls ein Coverfoto vorhanden ist,
stehen hier URLs dafür drin
 */

@Data
public class GImageLinks {
    String smallThumbnail;
    String thumbnail;
}
