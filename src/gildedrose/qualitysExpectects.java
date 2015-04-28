package gildedrose;

import java.io.Serializable;

/**
 * Created by luisolvera on 28/04/15.
 */
public class qualitysExpectects implements Serializable {

    public int getQualityExpect() {
        return qualityExpect;
    }

    public void setQualityExpect(int qualityExpect) {
        this.qualityExpect = qualityExpect;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int qualityExpect;
    private  int position;

}
