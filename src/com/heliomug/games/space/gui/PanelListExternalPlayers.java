package com.heliomug.games.space.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.heliomug.games.space.Player;
import com.heliomug.utils.gui.EtchedPanel;
import com.heliomug.utils.gui.UpdatingPanel;

@SuppressWarnings("serial")
public class PanelListExternalPlayers extends UpdatingPanel {
	public PanelListExternalPlayers() {
		super(new GridBagLayout());
		EtchedPanel.addEtch(this, "External Players");
	}
	
	@Override
	public void update() {
        removeAll();
        if (Frame.getClient() != null && Frame.getClient().getThing() != null) {
    		List<Player> externalPlayers = Frame.getClient().getThing().getPlayers();
    		externalPlayers.removeAll(Frame.getLocalPlayers());
        	if (externalPlayers != null & externalPlayers.size() > 0) {
	        	JLabel label;
	    		GridBagConstraints cons = new GridBagConstraints();
	    		cons.fill = GridBagConstraints.BOTH;
	    		cons.gridy = 0;
	    		cons.weightx = 1;
	    		
	    		cons.gridx = 0;
	    		add(new JLabel("Name", JLabel.CENTER), cons);
	    		cons.gridx = 1;
	    		add(new JLabel("Color", JLabel.CENTER), cons);
	    		
	    		cons.gridy++;
	    		
				for (Player player : externalPlayers) {
					cons.gridx = 0;
					label = new JLabel(player.toString(), JLabel.CENTER);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					add(label, cons);
					cons.gridx = 1;
					JPanel panel = new JPanel();
					panel.setBackground(player.getColor());
					add(panel, cons);
					cons.gridy++;
				}
        	} else {
    			add(new JLabel("no external players yet!"));
        	}
		} else {
			add(new JLabel("no external players yet!"));
		}
        revalidate();
	}

}