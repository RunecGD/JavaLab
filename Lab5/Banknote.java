package Labs.Lab5;

public class Banknote {

    private int denomination;     private int quantity;     
        public Banknote(int denomination, int quantity) {
        setDenomination(denomination);         setQuantity(quantity);     }

        public Banknote() {
        this(0, 0);     }

    public int getDenomination() {
        return denomination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDenomination(int denomination) {
        int[] validDenominations = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        boolean isValid = false;

        for (int validDenomination : validDenominations) {
            if (denomination == validDenomination) {
                isValid = true;
                break;
            }
        }

        if (isValid) {
            this.denomination = denomination;
        } else {
            throw new IllegalArgumentException("Неверный номинал. Допустимые значения: 1, 2, 5, 10, 20, 50, 100, 200, 500.");
        }
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Количество купюр должно быть больше 0.");
        }
    }

    public int calculateTotal() {
        return denomination * quantity;     }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Banknote banknote = (Banknote) obj;
        return denomination == banknote.denomination && quantity == banknote.quantity;
    }

    @Override
    public String toString() {
        return "Banknote{denomination=%d, quantity=%d, total=%d}".formatted(denomination, quantity, calculateTotal());
    }
}
