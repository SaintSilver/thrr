package thrr.asmr.finalproject.com.thrr;

/**
 * Created by pc-20 on 2018-01-09.
 */

public class MusicListVO {

    private int[] musicID = {
            R.raw.rain, R.raw.bird, R.raw.bug, R.raw.leaves, R.raw.cicada, R.raw.fire, R.raw.snow, R.raw.valley, R.raw.waterdrops, R.raw.wave,
            R.raw.blanket, R.raw.book, R.raw.chopping, R.raw.cream, R.raw.hairbrushing, R.raw.ice, R.raw.keyboard, R.raw.pencil, R.raw.piano, R.raw.scissors,
            R.raw.egg, R.raw.hairdryer, R.raw.plasticbag, R.raw.pountainpen, R.raw.sand, R.raw.shampoo, R.raw.slime, R.raw.soap, R.raw.train, R.raw.zengarden,
            R.raw.chicken, R.raw.conflakes, R.raw.cracker, R.raw.dango, R.raw.hotdog, R.raw.jelly, R.raw.macaron, R.raw.noodle, R.raw.pizza, R.raw.shrimppuffing,
            R.raw.carving, R.raw.ear, R.raw.earblowing, R.raw.hand, R.raw.heartbeat, R.raw.lids, R.raw.scratch, R.raw.shaving, R.raw.tapping, R.raw.walking };

    private int[] grayIcon = {R.drawable.rain7,R.drawable.bird7,R.drawable.bug7,R.drawable.leaves7,R.drawable.cicada7,R.drawable.fire7,R.drawable.snow7,R.drawable.valley7,R.drawable.waterdrop7,R.drawable.wave7,
            R.drawable.blanket27,R.drawable.book27,R.drawable.knife27,R.drawable.cream27,R.drawable.hiarbrush27,R.drawable.ice27,R.drawable.keyboard227,R.drawable.pencil7,R.drawable.piano27,R.drawable.scissors7,
            R.drawable.egg7,R.drawable.hairdryer27,R.drawable.plasticbag7,R.drawable.pountainpen7,R.drawable.sand7,R.drawable.shampoo7,R.drawable.slime7,R.drawable.soap7,R.drawable.train7,R.drawable.zengarden27,
            R.drawable.chicken27,R.drawable.conflakes27,R.drawable.cracker27,R.drawable.dango27,R.drawable.hotdog27,R.drawable.jelly27,R.drawable.macaron27,R.drawable.noodle7,R.drawable.pizza7,R.drawable.shrimppuffing7,
            R.drawable.carving27,R.drawable.ear7,R.drawable.earblow7,R.drawable.hand27,R.drawable.heart7,R.drawable.lids7,R.drawable.scratch7,R.drawable.shaving7,R.drawable.tapping7,R.drawable.walking7,
    };

    private int[] whiteIcon = {
            R.drawable.rain125, R.drawable.bird5, R.drawable.noun_308781_cc5, R.drawable.noun_1095263_cc5, R.drawable.cicada5, R.drawable.noun_7380_cc5, R.drawable.winter5, R.drawable.noun_3411_cc5, R.drawable.faucet5, R.drawable.wave5,
            R.drawable.blanket5, R.drawable.book5, R.drawable.knife5, R.drawable.cream5, R.drawable.hiarbrush5, R.drawable.ice5, R.drawable.keyboard5, R.drawable.pen5, R.drawable.piano5, R.drawable.scissors5,
            R.drawable.egg5, R.drawable.hairdryer5, R.drawable.plasticbag5, R.drawable.pountainpen5, R.drawable.sand5, R.drawable.shampoo5, R.drawable.sli5, R.drawable.soap5, R.drawable.train5, R.drawable.zengarden5,
            R.drawable.chicken5, R.drawable.conflakes5, R.drawable.cracker5, R.drawable.dango5, R.drawable.hotdog5, R.drawable.jelly5, R.drawable.macaron5, R.drawable.noodle5, R.drawable.pizza5, R.drawable.shrimppuffing5,
            R.drawable.carving5, R.drawable.ear5, R.drawable.earblow5, R.drawable.hand5, R.drawable.heart5, R.drawable.lid5, R.drawable.scratch5, R.drawable.shaving5, R.drawable.tapping5, R.drawable.walking5
    };

    private String[] musicName ={
            "rain", "bird", "cricket", "leaves", "cicada", "fire", "snow", "valley", "waterdrops", "wave",
            "blanket", "book", "chopping", "cream", "hairbrushing", "ice", "keyboard", "pencil", "piano", "scissors",
            "egg", "hairdryer", "plastic bag", "pountain pen", "sand", "shampoo", "slime", "soap", "train", "zengarden",
            "chicken", "conflakes", "cracker", "dango", "hotdog", "jelly", "macaron", "noodle", "pizza", "shrimp puffing",
            "carving", "ear", "ear blowing", "hand", "heartbeat", "lids", "scratch", "shaving", "tapping", "walking"
    };

    public int[] getMusicID() {
        return musicID;
    }

    public int[] getGrayIcon() {
        return grayIcon;
    }

    public int[] getWhiteIcon() {
        return whiteIcon;
    }

    public String[] getMusicName() {
        return musicName;
    }
}
