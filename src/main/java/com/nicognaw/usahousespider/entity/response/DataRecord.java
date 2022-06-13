package com.nicognaw.usahousespider.entity.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 爬取得到的数据，同时作为数据库Entity无损存入数据库.
 */
@Entity
public class DataRecord {
    public int RowNumber;
    @Id
    public long CommunityId;
    public double MinAskingRent;
    public double MaxAskingRent;
    public String PropertyTypeCategory;
    public String AddressLine1;
    public String AddressLine2;
    public String City;
    public String County;
    public String State;
    public String Zip;
    public double Latitude;
    public double Longitude;
    public int MinBedroomCount;
    public int MaxBedroomCount;
    public int MinFullBathCount;
    public int MinHalfBathCount;
    public int MaxFullBathCount;
    public int MaxHalfBathCount;
    public int YearBuilt;
    public int MinLivingArea;
    public int MaxLivingArea;
    public int PhotoCount;
    public int UnsafePhotoCount;
    public int AffordabilityStatus;
    public boolean IsPremiumListing;
    public boolean ShowSection8Badge;
    public boolean IsLeaseIncentives;
    public boolean IsSingleUnit;
    public boolean CollectModelApps;
    public int UnitCount;
    @Column(length = 2047)
    public String Title;
    @Column(length = 2047)
    public String Description;
    public String CommunityName = null;
    public String Photo;
    public boolean IsTenantFavourite;
    public boolean IsNewProperty;
    public boolean IsIncomeRestricted;
    public boolean IsJustUpdated;
    public String AvailabilityText;
    @JsonAlias("SeoFriendlyRentalUrl")
    public String seoFriendlyRentalUrl;

    public String getDescription() {
        return Description;
    }
}

