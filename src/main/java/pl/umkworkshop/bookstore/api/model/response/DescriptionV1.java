package pl.umkworkshop.bookstore.api.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;

public record DescriptionV1(String shortDescription, String longDescription) {

    @JsonCreator
    public DescriptionV1 {
    }
}
