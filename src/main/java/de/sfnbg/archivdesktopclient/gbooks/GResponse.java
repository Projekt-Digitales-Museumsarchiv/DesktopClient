package de.sfnbg.archivdesktopclient.gbooks;

import lombok.Data;

import java.util.List;

@Data
public class GResponse {
    Integer totalItems;
    List<GBook> items;
}
