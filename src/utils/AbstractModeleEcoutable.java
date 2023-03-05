package utils;

import java.util.ArrayList;

public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    private final ArrayList<EcouteurModele> listeEcouteurs;

    public AbstractModeleEcoutable(ArrayList<EcouteurModele> ecouteurs) {
        this.listeEcouteurs = ecouteurs;
    }
    public AbstractModeleEcoutable() {
        this(new ArrayList<>());
    }

    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        this.listeEcouteurs.add(e);
    }

    @Override
    public void retraitEcouteur(EcouteurModele e) {
        this.listeEcouteurs.remove(e);
    }

    protected void notifChangement() {
        for (EcouteurModele listeEcouteur : this.listeEcouteurs) {
            listeEcouteur.modeleMisAJour(this);
        }
    }
}
