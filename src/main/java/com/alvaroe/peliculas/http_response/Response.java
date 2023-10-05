package com.alvaroe.peliculas.http_response;

import lombok.Getter;
import lombok.Setter;
import java.util.Optional;

@Getter
@Setter
public class Response {

    private Object data;
    private Integer totalRecords;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private String next;
    private String previous;

    public Response(Object data, int totalRecords, Optional<Integer> page, int pageSize) {
        this.data = data;
        this.totalRecords = totalRecords;
        if (page.isPresent())
            buildPaginationMetaData(totalRecords, pageSize, page.get());
    }

    private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.totalPages = totalPages;

        if (page > 1 && totalPages > 1)
            this.previous = "/movies?page=" + (page - 1);
        if (page < totalPages)
            this.next = "/movies?page=" + (page + 1);
    }
}