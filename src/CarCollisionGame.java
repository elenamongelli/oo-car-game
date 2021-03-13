import java.util.Random;

class Car{
	Integer lives;	
	
	public Car() {	
	}
	
	public void setLives(Integer lives) {
		this.lives = lives;		
	}
	
	public boolean hasLives() {
		/*
		if (this.lives > 0) {
			return true;
		} else {
			return false;
		}
		*/
		return this.lives > 0;
	}
	
	public void hit(Obstacle o){		
		this.lives = this.lives + o.getIntensity();
	}
	
	public Integer getLives() {
		return this.lives;		
	}
}

class Score{
	
	Integer score = 0;
	
	public Score() {}
	
	public void increment() {
		this.score = this.score + 1; 
	}
	
	public Integer getScore() {
		return this.score;
	}
}


class Obstacle{
	Integer intensity;
	
	public void setIntensity(Integer intensity){
		this.intensity = intensity * -1;
	}
	
	public Integer getIntensity() {
		System.out.println(this.intensity);
		return this.intensity;
	}
}
 
class Truck extends Obstacle{}

class Pillar extends Obstacle{}

class Life extends Obstacle{
	public void setIntensity(Integer intensity){
		this.intensity = intensity;
	} 
}

public class CarCollisionGame {
	public static void main(String[] args) {
		
		Random random = new Random();
		Car c = new Car();
		c.setLives(10);
		
		Score s = new Score();
		while(c.hasLives()) {
			if (random.nextDouble() < .75) {
				System.out.println("Ouch! Obstacle hit!");
				Obstacle o = null;
				double r = random.nextDouble();
				if (r < 0.4) {
					o = new Truck(); // this should decrease the number of lives
					System.out.println("  That was a truck!");
				} else if ( r > 0.6) {
					o = new Pillar(); // this should decrease the number of lives
					System.out.println("  That was a pillar!");
				} else {
					o = new Life(); // this should increase the number of lives
					System.out.println("  That was a new life! Hurray :)");
				}
				o.setIntensity(1 + random.nextInt(3));
				c.hit(o);
				System.out.println("  Car has now " + String.valueOf(c.getLives()) + " lives");
			} else {
				System.out.println("No obstacles hit");
			}
			s.increment();
		}
		
		System.out.println("Game over");
		System.out.println("Final score is " + String.valueOf(s.getScore()));
	}
}