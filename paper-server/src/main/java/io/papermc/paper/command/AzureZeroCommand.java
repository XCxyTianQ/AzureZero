package io.papermc.paper.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;

import java.util.Collections;
import java.util.List;

@DefaultQualifier(NonNull.class)
public final class AzureZeroCommand extends Command {

    public AzureZeroCommand(final String name) {
        super(name);
        this.description = "Display AzureZero server info";
        this.usageMessage = "/azurezero";
        this.setPermission("azurezero.command.info");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
        return Collections.emptyList();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        sender.sendMessage(Component.text()
            .append(Component.text("零始青天，初擎云端", NamedTextColor.AQUA))
            .build()
        );
        sender.sendMessage(Component.text()
            .append(Component.text("From Zero to Azure — the sky is not the limit, it's the starting point.", NamedTextColor.GRAY))
            .build()
        );
        return true;
    }
}
