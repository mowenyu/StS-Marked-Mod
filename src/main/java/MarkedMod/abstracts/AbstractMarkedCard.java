package MarkedMod.abstracts;

import MarkedMod.actions.TriggerMarkAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.TriggerMarksAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;


@SuppressWarnings("unused")
public abstract class AbstractMarkedCard
        extends CustomCard {


    public AbstractMarkedCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardRarity rarity,
                              final CardTarget target) {

        this(id,
             languagePack.getCardStrings(id).NAME,
             img,
             cost,
             languagePack.getCardStrings(id).DESCRIPTION,
             type,
             CardColor.PURPLE,
             rarity,
             target);

    }


    public AbstractMarkedCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target) {

        this(id,
             languagePack.getCardStrings(id).NAME,
             img,
             cost,
             languagePack.getCardStrings(id).DESCRIPTION,
             type,
             color,
             rarity,
             target);

    }


    public AbstractMarkedCard(final String id,
                              final String name,
                              final String img,
                              final int cost,
                              final String rawDescription,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
    }


    public void applyMark(AbstractCreature p, AbstractCreature m) {
        this.applyMark(p, m, -1);
    }


    public void applyMark(AbstractCreature p, AbstractCreature m, int amount) {
        if (amount < 0) {
            amount = this.magicNumber;
        }

        this.addToBot(new ApplyPowerAction(m, p, new MarkPower(m, amount), amount));
    }


    public void triggerMarks() {
        this.addToBot(new TriggerMarksAction(this));
    }


    public void triggerMark(AbstractCreature monster) {
        this.addToBot(new TriggerMarkAction(monster));
    }
}