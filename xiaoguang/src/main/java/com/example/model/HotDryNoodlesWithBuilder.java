package com.example.model;

/**
 * Created by catherine on 17/5/17.
 *
 * builder 模式
 */

public class HotDryNoodlesWithBuilder {
    private boolean addShallot;//葱花.
    private boolean addParsley;//香菜.
    private boolean addChili;//辣椒.
    private boolean addSauerkraut;//酸菜

    public HotDryNoodlesWithBuilder(Builder builder) {
        this.addShallot = builder.addShallot;
        this.addParsley = builder.addParsley;
        this.addChili = builder.addChili;
        this.addSauerkraut = builder.addSauerkraut;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("A bowl of hot-dry noodles has:");
        if (this.addShallot) {
            stringBuilder.append("葱花,");
        }
        if (this.addParsley) {
            stringBuilder.append("香菜,");
        }
        if (this.addChili) {
            stringBuilder.append("辣椒,");
        }
        if (this.addSauerkraut) {
            stringBuilder.append("酸菜,");
        }
        return stringBuilder.toString();
    }

    public static class Builder {
        private boolean addShallot;//葱花.
        private boolean addParsley;//香菜.
        private boolean addChili;//辣椒.
        private boolean addSauerkraut;//酸菜

        public Builder withShallot() {
            this.addShallot = true;
            return this;
        }

        public Builder withParsley() {
            this.addParsley = true;
            return this;
        }

        public Builder withChili() {
            this.addChili = true;
            return this;
        }

        public Builder withSauerkraut() {
            this.addSauerkraut = true;
            return this;
        }

        public HotDryNoodlesWithBuilder build() {
            return new HotDryNoodlesWithBuilder(this);
        }
    }


}
