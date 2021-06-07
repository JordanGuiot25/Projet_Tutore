public class Test
{
	public static void main(String[]args)
	{
		Parterre p = new Parterre();
		Dalle b = new Dalle();
		Dalle c = new Dalle();
		Dalle d = new Dalle();
		Dalle e = new Dalle();
		Dalle f = new Dalle();
		Dalle g = new Dalle();
		Dalle h = new Dalle();
		Dalle i = new Dalle();
		Dalle j = new Dalle();
		System.out.println( p.ajouterDalle(b, 13, 14));
		for(String testc : b.getNomPosAllAdajcent())
		{
			System.out.println(testc);
		}
		for(String testc : b.getDalleAdjacent(1).getNomPosAllAdajcent())
		{
			System.out.println(testc);
		}
		
		

		
		
	}
}