package Enity;

public class OnlinePeople {
    private OwnPeople ownPeople;
	private int state;// -1未进入游戏、0未准备、1已准备、2进入游戏
    private int id;
    private int leave;

    public OnlinePeople(OwnPeople ownPeople, int state) {
        this.ownPeople = ownPeople;
        this.state = state;
        this.id = ownPeople.getPeople();
    }

    public OwnPeople getOwnPeople() {
        return ownPeople;
    }

    public void setOwnPeople(OwnPeople ownPeople) {
        this.ownPeople = ownPeople;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }
}
