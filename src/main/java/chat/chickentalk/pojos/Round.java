package chat.chickentalk.pojos;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SPOTLIGHTROUND")
public class Round {
	@Id
	@Column(name = "roundid")
	@SequenceGenerator(name = "ROUNDID_SEQ", sequenceName = "ROUNDID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROUNDID_SEQ")
	int id;
	
	@Column(name = "winnerid")
	int winnerId;
	
	@Column(name = "loserid")
	int loserId;
	
	@Column(name = "winnervotes")
	int winnerVotes;
	
	@Column(name = "loservotes")
	int loserVotes;
	
	@Column(name = "startdate")
	Timestamp startDate; // date and time the round started
	
	@Column(name = "enddate")
	Timestamp endDate; // date and time the round ended
	
	@Override
	public String toString() {
		return "Round [id=" + id + ", winnerId=" + winnerId + ", loserId=" + loserId + ", winnerVotes=" + winnerVotes
				+ ", loserVotes=" + loserVotes + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	Round(){}

	public Round(int id, int winnerId, int loserId, int winnerVotes, int loserVotes, Timestamp startDate, Timestamp endDate) {
		super();
		this.id = id;
		this.winnerId = winnerId;
		this.loserId = loserId;
		this.winnerVotes = winnerVotes;
		this.loserVotes = loserVotes;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}

	public int getLoserId() {
		return loserId;
	}

	public void setLoserId(int loserId) {
		this.loserId = loserId;
	}

	public int getWinnerVotes() {
		return winnerVotes;
	}

	public void setWinnerVotes(int winnerVotes) {
		this.winnerVotes = winnerVotes;
	}

	public int getLoserVotes() {
		return loserVotes;
	}

	public void setLoserVotes(int loserVotes) {
		this.loserVotes = loserVotes;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
}
