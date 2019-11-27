import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageMiner extends ListenerAdapter
{
	private final MessageWriterService messageWriter;

	public MessageMiner()
	{
		messageWriter = new MessageWriterService();
	}

	@Override
	public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event)
	{
		final String id = event.getGuild().getId();
		final String channelID = event.getMessage().getChannel().getId();
		final String message = event.getMessage().getContentStripped();
		if (!event.getMessage().getAuthor().isBot())
		{
			MinedMessage minedMessage = new MinedMessage(id, channelID, message);
			if (minedMessage.getMessage().length() > 0)
			{
				messageWriter.writeMinedMessage(minedMessage);
			}
		}
	}
}
