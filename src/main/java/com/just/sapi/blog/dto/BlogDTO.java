package com.just.sapi.blog.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class BlogDTO {
    private String title;
    private String contents;
    private String url;
    @JsonProperty("blogname")
    private String blogName;
    @JsonProperty("thumbnail")
    private String thumbnailUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime datetime;

    @Builder
    public BlogDTO(String title, String contents, String url, String blogName, String thumbnailUrl, OffsetDateTime datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogName = blogName;
        this.thumbnailUrl = thumbnailUrl;
        this.datetime = datetime;
    }
}
