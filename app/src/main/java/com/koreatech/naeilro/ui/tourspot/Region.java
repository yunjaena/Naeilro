package com.koreatech.naeilro.ui.tourspot;

import com.koreatech.naeilro.R;

public enum Region {
    DEFAULT(0, 0, Integer.MAX_VALUE, 0, R.array.default_array),
    SEOUL(1, 1, Integer.MAX_VALUE, 0, R.array.seoul_array),
    INCHEON(2, 2, Integer.MAX_VALUE, 0, R.array.incheon_array),
    DAEJEON(3, 3, Integer.MAX_VALUE, 0, R.array.dajeon_array),
    DAEGU(4, 4, Integer.MAX_VALUE, 0, R.array.daegu_array),
    GANGJU(5, 5, Integer.MAX_VALUE, 0, R.array.gangju_array),
    BUSAN(6, 6, Integer.MAX_VALUE, 0, R.array.busan_array),
    ULSAN(7, 7, Integer.MAX_VALUE, 0, R.array.ulsan_array),
    SAEJONG(8, 8, Integer.MAX_VALUE, 0, R.array.saejong_array),
    GUNGGI(9, 31, Integer.MAX_VALUE, 0, R.array.gunggi_array),
    GANGWON(10, 32, Integer.MAX_VALUE, 0, R.array.gangwon_array),
    CHUNGCHUNGBUK(11, 33, Integer.MAX_VALUE, 0, R.array.chungchungbuk_array),
    CHUNGCHUNGNAM(12, 34, 9, 1, R.array.chungchungnam_array),
    GYUNGSANGBUK(13, 35, Integer.MAX_VALUE, 0, R.array.gyungsangbuk_array),
    GYUNGSANGNAM(14, 36, 10, 1, R.array.gyungsangnam_array),
    JUNRABUK(15, 37, Integer.MAX_VALUE, 0, R.array.junrabuk_array),
    JUNRANAMK(16, 38, 13, 2, R.array.junranam_array),
    JEJU(17, 39, Integer.MAX_VALUE, 0, R.array.jeju_array),
    ;

    private int areaIndex;
    private int areaCode;
    private int sigunguDifferenceIndex;
    private int sigunguCodeDifference;
    private int sigunguArrayID;

    Region(int areaIndex, int areaCode, int sigunguDifferenceIndex, int sigunguCodeDifference, int sigunguArrayID) {
        this.areaIndex = areaIndex;
        this.areaCode = areaCode;
        this.sigunguDifferenceIndex = sigunguDifferenceIndex;
        this.sigunguCodeDifference = sigunguCodeDifference;
        this.sigunguArrayID = sigunguArrayID;
    }

    public static Region getRegionOrDefault(int index) {
        for (Region region : values()) {
            if (index == region.getAreaIndex()) {
                return region;
            }
        }
        return values()[0];
    }

    public int getAreaIndex() {
        return areaIndex;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getSigunguDifferenceIndex() {
        return sigunguDifferenceIndex;
    }

    public int getSigunguCodeDifference() {
        return sigunguCodeDifference;
    }

    public int getSigunguArrayID() {
        return sigunguArrayID;
    }
}
