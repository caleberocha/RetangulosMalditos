package pucrs.alest2.t1;

public enum Color {
	AMARELO((byte)1, "amarelo"),
	AZULCLARO((byte)2, "azul-claro"),
	AZULESCURO((byte)3, "azul-escuro"),
	CINZA((byte)4, "cinza"),
	DOURADO((byte)5, "dourado"),
	LARANJA((byte)6, "laranja"),
	MARROM((byte)7, "marrom"),
	PRETO((byte)8, "preto"),
	VERDECLARO((byte)9, "verde-claro"),
	VERDEESCURO((byte)10, "verde-escuro"),
	VERMELHO((byte)11, "vermelho"),
	VIOLETA((byte)12, "violeta");
	
	private byte value;
	private String description;
	
	Color(byte value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public byte value() {
		return value;
	}
	
	public String description() {
		return description;
	}
	
	public static byte byteOf(String color) {
		for(Color c : values())
			if(c.description.equals(color))
				return c.value;
		
		return -1;
	}
	
	public static String descriptionOf(byte value) {
		for(Color c : values())
			if(c.value == value)
				return c.description;
		
		return null;
	}
	
	public static String descriptionOf(int value) {
		return descriptionOf((byte)value);
	}
}
