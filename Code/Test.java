public class Test
{

	public static void main( String[] a)
	{

		Parterre plateau = new Parterre();

		for ( Dalle dalle : plateau.getDalles() )
		{
			System.out.println(dalle );
		}

		for ( Dalle dalle : plateau.getDalles() )
		{
			System.out.println(dalle.getNom() + " : "+ dalle.getX() + " "+ dalle.getY() );
		}
	}
}