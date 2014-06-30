package org.nem.core.model;

public class m {
    private static m bs = m.aS();
    private static m bt = m.aT();
    private byte bu;
    private char bv;
    private String bw;
    private String[] bx;

    public byte aL() {
        return this.bu;
    }

    public char aM() {
        return this.bv;
    }

    public String aN() {
        return this.bw;
    }

    public String[] aO() {
        return this.bx;
    }

    public static m aP() {
        return m.bs;
    }

    public static m aQ() {
        return m.bt;
    }

    public static m aR() {
        return m.aQ();
    }

    private static m aS() {
        m m = new m();
        m.bu = 104;
        m.bv = 78;
        m.bw = "Not-a-real-address-0";
        m.bx = new String[]{"Not-a-real-address-1", "Not-a-real-address-2", "Not-a-real-address-3", "Not-a-real-address-4", "Not-a-real-address-5", "Not-a-real-address-6", "Not-a-real-address-7", "Not-a-real-address-8"};
        return m;
    }

    private static m aT() {
        m m = new m();
        m.bu = -104;
        m.bv = 84;
        m.bw = "TBERUJIKSAPW54YISFOJZ2PLG3E7CACCNP3PP3P6";
        m.bx = new String[]{"TbloodZW6W4DUVL4NGAQXHZXFQJLNHPDXHULLHZW", "TAthiesMY6QO6XKPCBZFEVVVFVL2UT3ESDHAVGL7", "TDmakotEWZNTXYDSCYKAVGRHFSE6K33BSUATKQBT", "TDpatemA4HXS7D44AQNT6VH3AHKDSNVC3MYROLEZ", "TBgimreUQQ5ZQX6C3IGLBSVPMROHCMPEIHY4GV2L", "TDIUWEjaguaWGXI56V5MO7GJAQGHJXE2IZXEK6S5", "TCZloitrAOV4F5J2H2ACC4KXHHTKLQHN3G7HV4B4", "TDHDSTFY757SELOAE3FU7U7krystoP6FFB7XXSYH", "TD53NLTDK7EMSutopiAK4RSYQ523VBS3C62UMJC5"};
        return m;
    }
}