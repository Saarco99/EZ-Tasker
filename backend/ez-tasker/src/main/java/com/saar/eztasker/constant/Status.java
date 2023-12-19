package com.saar.eztasker.constant;

public enum Status {
    Pending{
        @Override
        public String toString() {
            return "Pending";
        }

    },
    Completed{
        @Override
        public String toString() {
            return "Completed";
        }
    },
}
