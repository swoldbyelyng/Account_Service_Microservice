package user_service.model;

import org.json.JSONObject;

public class Cannabis {

    private int idCannabis;
    private String CannabisName;
    private int idEffect;
    private int idSideEffect;
    private int idCatagory;
    private float THCRatio;
    private float CBGRatio;
    private float CBDRatio;
    private String Description;






    // Default constructor
    public Cannabis() {
    }

    public Cannabis(String CannabisName) {
        this.CannabisName = CannabisName;

    }

    public int getIdCannabis(){
        return idCannabis;
    }

    public void setIdCannabis(int idCannabis) {
        this.idCannabis = idCannabis;
    }

    public String getCannabisName() {
        return CannabisName;
    }
    public void setCannabisName(String CannabisName) {

        this.CannabisName = CannabisName;
    }

    public int getIdEffect(){
        return idEffect;
    }

    public void setIdEffect(int idEffect) {
        this.idEffect = idEffect;
    }

    public int getIdSideEffect(){
        return idSideEffect;
    }

    public void setIdSideEffect(int idSideEffect) {
        this.idSideEffect = idSideEffect;
    }

    public int getIdCatagory(){
        return idCatagory;
    }

    public void setIdCatagory(int idCatagory) {
        this.idCatagory = idCatagory;
    }

    public float getTHCRatio() {
        return THCRatio;
    }

    public void setTHCRatio(float THCRatio) {
        this.THCRatio = THCRatio;
    }


    public float getCBDRatio() {
        return CBDRatio;
    }

    public void setCBDRatio(float CBDRatio) {
        this.CBDRatio = CBDRatio;
    }

    public float getCBGRatio() {
        return CBGRatio;
    }

    public void setCBGRatio(float CBGRatio) {
        this.CBGRatio = CBGRatio;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Note: The JSONObject doesn't include the password
     */
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("CannabisName", CannabisName);
        return json;
    }

    /**
     * Note: The JSONObject doesn't include the password
     */
    public static Cannabis fromJSONObject(JSONObject json, boolean requirePassword) {
        Cannabis Cannabis = new Cannabis();
        Cannabis.CannabisName = json.getString("Cannabis");

        return Cannabis;
    }

    @Override
    public String toString() {
        return CannabisName + " + this.CannabisName";
    }
}

