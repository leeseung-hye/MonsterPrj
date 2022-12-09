package kr.kosa.mon;


public class MonVo {
   private int monId;
   private String monName;
   private int monLevel;
   private String regionList;
   private String typeList; 
   private int starList; //몬스터난이도
   private String difficultyList; //체감난이도
   
   public MonVo() {
      super();
   }
   
   public int getMonId() {
      return monId;
   }
   public void setMonId(int monId) {
      this.monId = monId;
   }
   public String getMonName() {
      return monName;
   }
   public void setMonName(String monName) {
      this.monName = monName;
   }
   public int getMonLevel() {
      return monLevel;
   }
   public void setMonLevel(int monLevel) {
      this.monLevel = monLevel;
   }
   public String getRegionList() {
      return regionList;
   }
   public void setRegionList(String regionList) {
      this.regionList = regionList;
   }
   public String getTypeList() {
      return typeList;
   }
   public void setTypeList(String typeList) {
      this.typeList = typeList;
   }
   public int getStarList() {
      return starList;
   }
   public void setStarList(int starList) {
      this.starList = starList;
   }
   public String getDifficultyList() {
      return difficultyList;
   }
   public void setDifficultyList(String difficultyList) {
      this.difficultyList = difficultyList;
   }

   @Override
   public String toString() {
      return "MonVo [monId=" + monId + ", monName=" + monName + ", monLevel=" + monLevel + ", regionList="
            + regionList + ", typeList=" + typeList + ", starList=" + starList + ", difficultyList="
            + difficultyList + "]";
   }
}