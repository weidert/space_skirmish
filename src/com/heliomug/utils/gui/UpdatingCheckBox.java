package com.heliomug.utils.gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class UpdatingCheckBox extends JCheckBox {
    Supplier<Boolean> source;
    public UpdatingCheckBox(String title, Consumer<Boolean> dest, Supplier<Boolean> source) {
    	this(title, 0, dest, source);
    }
    
    public UpdatingCheckBox(String title, int mne, Consumer<Boolean> dest, Supplier<Boolean> source) {
        super(title);
        
        if (mne != 0) {
        	setMnemonic(mne);
        }
        
        this.source = source;

        setFocusable(false);
        
        addActionListener((ActionEvent e) -> {
                dest.accept(isSelected());
        });
    }
    
    @Override
    public void paint(Graphics g) {
        this.setSelected(source.get());
        super.paint(g);
    }
}
