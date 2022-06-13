package com.nicognaw.usahousespider.entity.request;

/**
 * 搜索接口参数.
 */
@SuppressWarnings("ALL")
public class SearchParameters {
    public int landlordUserId = 0;
    public String state = "";
    public String county = "";
    public String city = "";
    public String zip = "";
    public int minPrice = 0;
    public int maxPrice = 0;
    public boolean hasSection8Voucher = false;
    public int yearlyIncome = 0;
    public int familySize = -1;
    public int voucherSize = -1;
    public boolean isExcludeExceedsEligibility = false;
    public String bedCounts = "";
    public int requiredMoreBedCounts = 0;
    public int bathCount = 0;
    public String sortExpression = "LastUpdate Desc";
    /**
     * 这个参数指定了查询结果中每一页有几项数据.
     */
    public int itemsPerPage = 100000;
    public int page = 1;
    public boolean returnOnlyCount = false;
    public int minLivingArea = 0;
    public int maxLivingArea = 0;
    public String propertyTypeCategories = "";
    public boolean inUnitWasherAndDryer = false;
    public boolean onsiteLaundryFacilities = false;
    public boolean balconyPatio = false;
    public boolean parking = false;
    public boolean fitnessCenter = false;
    public boolean communitySwimmingPool = false;
    public boolean airConditioning = false;
    public boolean dishwasher = false;
    public boolean utilitiesIncluded = false;
    public boolean elevator = false;
    public boolean ageRestricted = false;
    public boolean noAgeRestrictions = false;
    public boolean petFriendly = false;
    public boolean leaseIncentives = false;
    public boolean picturesOnly = false;
    public boolean showSection8Badge = false;
    public boolean physical = false;
    public boolean visualHearing = false;
    public String keywordSearch = "";
    /**
     * 这个参数不可用，设为真后服务器会500.
     */
    public boolean returnIdsOnly = false;
    public int isNewLocation = 0;
    /**
     * 这个参数如果是真，则会附带地图上显示的小区信息.
     */
    public boolean isIncludeMapListing = false;
    /**
     * 目标系统采用 {@linkp #https://www.mapbox.com} 地图系统，坐标的最值如以下默认属性.
     */
    public double maxLatitude = 90;
    public double maxLongitude = 180;
    public double minLatitude = -90;
    public double minLongitude = -180;
    public int maxMarkerToShow = 500;
    public int schoolId = 0;
    public int NCESSchoolID = 0;
    public String education = "";
    public String pos = "90,180,-90,-180";
    public int zoom = 3;
    public String center = "0,0";
    public String HaId = "0";

    public SearchParameters() {
    }

    public SearchParameters(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
