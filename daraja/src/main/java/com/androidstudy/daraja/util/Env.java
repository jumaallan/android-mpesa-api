package com.androidstudy.daraja.util;

import com.androidstudy.daraja.network.URLs;

public enum Env {

    SANDBOX {
        @Override
        public String toString() {
            return URLs.SANDBOX_BASE_URL;
        }
    },


    PRODUCTION {
        @Override
        public String toString() {
            return URLs.PRODUCTION_BASE_URL;
        }
    }
}
