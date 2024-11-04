package com.match.dev.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.match.dev.entity.Match;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;

public class MatchOddsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1028443829130910060L;

    @JsonIgnore
    private Match match;

    private String specifier;

    @Positive
    private float odd;

    public MatchOddsDto() {
    }

    private MatchOddsDto(Builder builder) {
        setMatch(builder.match);
        setSpecifier(builder.specifier);
        setOdd(builder.odd);
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public float getOdd() {
        return odd;
    }

    public void setOdd(float odd) {
        this.odd = odd;
    }

    public static class Builder {

        private Match match;
        private String specifier;
        private float odd;

        public Builder withMatch(Match match) {
            this.match = match;
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

        public MatchOddsDto build() {
            return new MatchOddsDto(this);
        }
    }
}


