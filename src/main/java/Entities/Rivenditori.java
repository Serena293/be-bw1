package Entities;

   public abstract class Rivenditori {

    private int bigliettiEmessi;
    private int abbonamentiEmessi;

    public Rivenditori(int bigliettiEmessi, int abbonamentiEmessi ) {
        this.bigliettiEmessi = bigliettiEmessi;
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

    public int getBigliettiEmessi() {
        return bigliettiEmessi;
    }

    public void setBigliettiEmessi(int bigliettiEmessi) {
        this.bigliettiEmessi = bigliettiEmessi;
    }

    public int getAbbonamentiEmessi() {
        return abbonamentiEmessi;
    }

    public void setAbbonamentiEmessi(int abbonamentiEmessi) {
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

}
