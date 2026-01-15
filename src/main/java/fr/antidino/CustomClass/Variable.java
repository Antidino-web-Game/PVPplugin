package fr.antidino.CustomClass;

// Définissez le nom des variables et leur type 
public class Variable {
    private String nom;
    private Boolean state;

    // Constructeur avec deux valeurs
    public Variable(String n, Boolean st) {
        this.nom = n;
        this.state = st;
    }

    // Accesseur pour obtenir la valeur1
    public String getName() {
        return nom;
    }

    // Accesseur pour configurer la valeur1
    public void setName(String n) {
        this.nom = n;
    }

    // Accesseur pour obtenir la valeur2
    public boolean getStat() {
        return state;
    }

    // Accesseur pour configurer la valeur2
    public void setValeur2(Boolean valeur) {
        this.state = valeur;
    }
}