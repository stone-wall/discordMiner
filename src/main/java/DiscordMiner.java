import java.io.IOException;
import java.util.Properties;
import javax.security.auth.login.LoginException;
import lombok.Getter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Getter
public class DiscordMiner
{
	Properties tokenProperties = new Properties();
	private String botToken;
	private JDA jda;

	public DiscordMiner()
	{
		try
		{
			Properties tokenProperties = new Properties();
			tokenProperties.load(DiscordMiner.class.getClassLoader().getResourceAsStream("token.properties"));
			botToken = tokenProperties.getProperty("token");
			jda = new JDABuilder(AccountType.CLIENT)
				.setToken(botToken)
				.addEventListeners(new MessageMiner())
				.build();
			jda.awaitReady();
		}
		catch (IOException | InterruptedException | LoginException e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args)
	{
		DiscordMiner miner = new DiscordMiner();
	}
}
