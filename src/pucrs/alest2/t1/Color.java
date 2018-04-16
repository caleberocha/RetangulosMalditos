package pucrs.alest2.t1;

/**
 * Possíveis cores para os retângulos.
 * É armazenado um valor do tipo byte e uma string com o nome da cor para cada entrada, para economizar memória (apenas o byte deve ser armazenado nas operações que utilizam este Enum).
 * @author calebe
 *
 */
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
	
	/**
	 * Valor (byte) da cor.
	 * @return
	 */
	public byte value() {
		return value;
	}
	
	/**
	 * Nome da cor.
	 * @return
	 */
	public String description() {
		return description;
	}
	
	/**
	 * Obtém o valor (byte) correspondente ao nome especificado.
	 * @param color
	 * @return
	 */
	public static byte byteOf(String color) {
		for(Color c : values())
			if(c.description.equals(color))
				return c.value;
		
		return -1;
	}
	
	/**
	 * Obtém o nome da cor correspondente ao valor (byte) especificado.
	 * @param value
	 * @return
	 */
	public static String descriptionOf(byte value) {
		for(Color c : values())
			if(c.value == value)
				return c.description;
		
		return null;
	}
	
	/**
	 * Obtém o nome da cor correspondente ao valor especificado.
	 * @param value
	 * @return
	 */
	public static String descriptionOf(int value) {
		return descriptionOf((byte)value);
	}
}
