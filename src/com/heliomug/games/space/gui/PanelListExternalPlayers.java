package com.heliomug.games.space.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.heliomug.games.space.Player;
import com.heliomug.utils.gui.UpdatingScrollPanel;

@SuppressWarnings("serial")
public class PanelListExternalPlayers extends UpdatingScrollPanel {
	public PanelListExternalPlayers() {
		super("External Players");
	}
	
	@Override
	public void update() {
        JPanel panel = getListPanel();
		panel.removeAll();
        
        List<Player> externalPlayers = SpaceFrame.getExternalPlayers();
		externalPlayers.removeAll(SpaceFrame.getLocalPlayers());
    	if (externalPlayers != null & externalPlayers.size() > 0) {
        	JLabel label;
    		GridBagConstraints cons = new GridBagConstraints();
    		cons.fill = GridBagConstraints.BOTH;
    		cons.gridy = 0;
    		cons.weightx = 1;
    		
    		cons.gridx = 0;
    		panel.add(new JLabel("Name", JLabel.CENTER), cons);
    		cons.gridx++;
    		panel.add(new JLabel("Color", JLabel.CENTER), cons);
    		
    		cons.gridy++;
    		
			for (Player player : externalPlayers) {
				cons.gridx = 0;
				label = new JLabel(player.getName(), JLabel.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(label, cons);
	    		cons.gridx++;
				JPanel colorPanel = new JPanel();
				colorPanel.setBackground(player.getColor());
				colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(panel, cons);
				cons.gridy++;
			}
    	} else {
			panel.add(new JLabel("no external players yet!"));
    	}
        revalidate();
	}
}
