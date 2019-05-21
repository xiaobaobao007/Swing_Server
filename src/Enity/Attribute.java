package Enity;

import java.io.DataOutputStream;
import java.io.IOException;

import Controller.OnlinePeopleController;
import GameStart.ServerStart;

public class Attribute implements Cloneable {

    private int x;
    private int y;
    private int id;
    private int imgId;
    private String name;
    private int power;// 力量
    private int magic;// 魔力
    private int skill;// 技巧
    private int speed;// 速度
    private int physical;// 体质
    private int armor;// 护甲
    private int resistance;// 抗性

    // private int con_phy_attack;// 物理伤害系数
    // private int con_mag_attack;// 法术伤害系数
    // private int con_MP;// 魔力值系数
    // private int con_blow;// 暴击系数
    // private int con_dodge;// 闪避率 系数
    // private int con_speed_action;// 行动速度系数
    // private int con_HP;// 生命系数
    // private int con_phy_defense;// 物理防御系数
    // private int con_mag_defense;// 法术防御系数

    private int con_phy_attack;// 物理伤害系数
    private int con_mag_attack;// 法术伤害系数
    private int con_MP;// 魔力值系数
    private int con_blow;// 暴击系数
    private int con_shooting;// 命中系数
    private int con_dodge;// 闪避率 系数
    private int con_speed_action;// 行动速度系数
    private int con_HP;// 生命系数
    private int con_phy_defense;// 物理防御系数
    private int con_mag_defense;// 法术防御系数

    private int phy_attack;// 物理伤害
    private int mag_attack;// 法术伤害
    private int MP;// 魔力值
    private int blow;// 暴击
    private int shooting;// 命中
    private int dodge;// 闪避率
    private int speed_action;// 行动速度
    private int HP;// 生命
    private int phy_defense;// 物理防御
    private int mag_defense;// 法术防御

    private int money;//钱

    public Attribute(int x, int y, int id,int imgId, String name, int money, int con_phy_attack, int con_mag_attack, int con_MP, int// 系数设定
            con_blow, int con_shooting, int con_dodge, int con_speed_action, int con_HP, int con_phy_defense, int con_mag_defense, int power,
                     int magic, int skill, int speed, int physical, int armor, int resistance, DataOutputStream dos) {// 属性设定
        super();
        this.x = x;
        this.y = y;
        this.id = id;
        this.imgId = imgId;
        this.name = name;
        this.money = money;
        this.con_phy_attack = con_phy_attack;
        this.con_mag_attack = con_mag_attack;
        this.con_MP = con_MP;
        this.con_blow = con_blow;
        this.con_shooting = con_shooting;
        this.con_dodge = con_dodge;
        this.con_speed_action = con_speed_action;
        this.con_HP = con_HP;
        this.con_phy_defense = con_phy_defense;
        this.con_mag_defense = con_mag_defense;
        this.power = power;
        this.magic = magic;
        this.skill = skill;
        this.speed = speed;
        this.physical = physical;
        this.armor = armor;
        this.resistance = resistance;
        setPhy_attack(power);
        setMag_attack(magic);
        setMP(magic);
        setBlow(skill);
        setShooting(skill);
        setDodge(speed);
        setSpeed_action(speed);
        setHP(physical);
        setPhy_defense(armor);
        setMag_defense(resistance);
    }

    public String getString() {
        return ";" + x + ":" + y + ":" + id + ":"+ imgId + ":" + name + ":" + money + ":" + con_phy_attack + ":" + con_mag_attack + ":" + con_MP + ":"
                + con_blow + ":" + con_shooting + ":" + con_dodge + ":" + con_speed_action + ":" + con_HP + ":" + con_phy_defense + ":"
                + con_mag_defense + ":" + power + ":" + magic + ":" + skill + ":" + speed + ":" + physical + ":"
                + armor + ":" + resistance;
    }

    public String getTeamString() {
        return ":" + x + ":" + y + ":" + id + ":"+ imgId + ":" + name;
    }

    @Override
    public Attribute clone() {
        Attribute a = null;
        try {
            a = (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getCon_phy_attack() {
        return con_phy_attack;
    }

    public void setCon_phy_attack(int con_phy_attack) {
        this.con_phy_attack = con_phy_attack;
    }

    public int getCon_mag_attack() {
        return con_mag_attack;
    }

    public void setCon_mag_attack(int con_mag_attack) {
        this.con_mag_attack = con_mag_attack;
    }

    public int getCon_MP() {
        return con_MP;
    }

    public void setCon_MP(int con_MP) {
        this.con_MP = con_MP;
    }

    public int getCon_blow() {
        return con_blow;
    }

    public void setCon_blow(int con_blow) {
        this.con_blow = con_blow;
    }

    public int getCon_shooting() {
        return con_shooting;
    }

    public void setCon_shooting(int con_shooting) {
        this.con_shooting = con_shooting;
    }

    public int getCon_dodge() {
        return con_dodge;
    }

    public void setCon_dodge(int con_dodge) {
        this.con_dodge = con_dodge;
    }

    public int getCon_speed_action() {
        return con_speed_action;
    }

    public void setCon_speed_action(int con_speed_action) {
        this.con_speed_action = con_speed_action;
    }

    public int getCon_HP() {
        return con_HP;
    }

    public void setCon_HP(int con_HP) {
        this.con_HP = con_HP;
    }

    public int getCon_phy_defense() {
        return con_phy_defense;
    }

    public void setCon_phy_defense(int con_phy_defense) {
        this.con_phy_defense = con_phy_defense;
    }

    public int getCon_mag_defense() {
        return con_mag_defense;
    }

    public void setCon_mag_defense(int con_mag_defense) {
        this.con_mag_defense = con_mag_defense;
    }

    public int getPhy_attack() {
        return phy_attack;
    }

    public void setPhy_attack(int power) {
        this.phy_attack = this.power * this.con_phy_attack;
    }

    public int getMag_attack() {
        return mag_attack;
    }

    public void setMag_attack(int magic) {
        this.mag_attack = magic * this.con_mag_attack;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int magic) {
        this.MP = magic * this.con_MP;
    }

    public int getBlow() {
        if ((int) (Math.random() * 1000) <= this.blow) {
            return blow;
        } else
            return -1;
    }

    public void setBlow(int skill) {
        this.blow = skill * this.con_blow;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int skill) {
        this.shooting = skill * this.shooting;
    }

    public int getDodge() {
        if ((int) (Math.random() * 1000) <= this.dodge) {
            return 1;
        } else
            return -1;
    }

    public void setDodge(int speed) {
        this.dodge = speed * this.con_dodge;
    }

    public int getSpeed_action() {
        return speed_action;
    }

    public void setSpeed_action(int speed) {
        this.speed_action = speed * this.con_speed_action;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int physical) {
        this.HP = physical * this.con_HP;
    }

    public int getPhy_defense() {
        return phy_defense;
    }

    public void setPhy_defense(int armor) {
        this.phy_defense = armor * this.con_phy_defense;
    }

    public int getMag_defense() {
        return mag_defense;
    }

    public void setMag_defense(int resistance) {
        this.mag_defense = resistance * this.con_mag_defense;
    }
}
