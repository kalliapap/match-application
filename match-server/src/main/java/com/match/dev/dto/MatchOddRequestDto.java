package com.match.dev.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class MatchOddRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1028443829130910060L;

    private Long id;

    private long matchId;

    @NotNull
    private String specifier;

    @NotNull
    private Float odd;

    public MatchOddRequestDto() {
    }

    private MatchOddRequestDto(Builder builder) {
        setId(builder.id);
        setMatchId(builder.matchId);
        setSpecifier(builder.specifier);
        setOdd(builder.odd);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public void setOdd(@NotNull Float odd) {
        this.odd = odd;
    }

    public @NotNull Float getOdd() {
        return odd;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public static class Builder {

        private long id;
        private long matchId;
        private String specifier;
        private float odd;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withMatchId(long matchId) {
            this.matchId = matchId;
            return this;
        }

        public Builder withSpecifier(String specifier) {
            this.specifier = specifier;
            return this;
        }

        public Builder withOdd(float odd) {
            this.odd = odd;
            return this;
        }

        public MatchOddRequestDto build() {
            return new MatchOddRequestDto(this);
        }
    }
}


