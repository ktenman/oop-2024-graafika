package ee.ut.graafika;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Operatsioonid {
	
	default BigDecimal arvuta(BigDecimal arv1, BigDecimal arv2, String tehe) {
		return switch (tehe) {
			case "+" -> arv1.add(arv2);
			case "-" -> arv1.subtract(arv2);
			case "*" -> arv1.multiply(arv2);
			case "/" -> {
				if (arv2.equals(BigDecimal.ZERO)) {
					throw new ArithmeticException("Nulliga jagamine");
				} else {
					yield arv1.divide(arv2, 10, RoundingMode.HALF_UP);
				}
			}
			default -> throw new IllegalArgumentException("Tundmatu tehe: " + tehe);
		};
	}
}
