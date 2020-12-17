package com.Assignment.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FinalResponse implements Serializable {

    @JsonProperty("finalResponse")
    List<VoteInfoFromQueue> finalResponse;
}
