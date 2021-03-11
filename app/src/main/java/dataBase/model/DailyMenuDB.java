package dataBase.model;

import model.DailyMenu;

public class DailyMenuDB extends FoodDB{

    private int limit;

    public DailyMenuDB(){}

    public DailyMenuDB(DailyMenu dailyMenu) {
        super(dailyMenu.getId(),dailyMenu.getName(),dailyMenu.getDescription(),dailyMenu.getImgId(),dailyMenu.getProductCategory(),dailyMenu.getConditions(),dailyMenu.getStock2(),dailyMenu.getPrice2());
        this.limit = dailyMenu.getDailyLimit();
    }

    public DailyMenu convertToModel(){
        DailyMenu m =  new DailyMenu(super.getId(),
                super.getName(),
                super.getDescription(),
                super.getImgId(),
                super.getProductCategory(),
                super.getStock(),
                super.getPrice(),
                limit
        );
        for(Integer i : super.getConditions())
            m.addCondition(i);
        return m;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
