/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.sbapr.gui.DataSourcePanels;

import cz.muni.fi.sbapr.utils.IterableNodeList;
import cz.muni.fi.sbapr.utils.RGHelper;
import java.awt.Dialog;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Adam
 */
public class IMGDataSourcePanel extends DataSourcePanel {

    /**
     * Creates new form IMGDataSourcePanel
     */
    public IMGDataSourcePanel(Dialog parent) {
        super(parent);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldURL = new javax.swing.JTextField();
        fieldWidth = new javax.swing.JTextField();
        fieldHeight = new javax.swing.JTextField();
        checkBoxResize = new javax.swing.JCheckBox();

        fieldURL.setText("URL");
        fieldURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldURLActionPerformed(evt);
            }
        });

        fieldWidth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldWidth.setText("WIDTH");
        fieldWidth.setEnabled(false);

        fieldHeight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldHeight.setText("HEIGHT");
        fieldHeight.setEnabled(false);

        checkBoxResize.setText("Resize");
        checkBoxResize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxResizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldURL)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addComponent(checkBoxResize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldURL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxResize))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fieldURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldURLActionPerformed

    private void checkBoxResizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxResizeActionPerformed
        fieldHeight.setEnabled(checkBoxResize.isSelected());
        fieldWidth.setEnabled(checkBoxResize.isSelected());
    }//GEN-LAST:event_checkBoxResizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxResize;
    private javax.swing.JTextField fieldHeight;
    private javax.swing.JTextField fieldURL;
    private javax.swing.JTextField fieldWidth;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean updateElement(Element element) {
        new IterableNodeList(element.getChildNodes()).forEach(child -> element.removeChild(child));

        Document doc = RGHelper.INSTANCE.getDoc();
        Element urlElement;
        try {
            URL url = new URL(fieldURL.getText());
            urlElement = doc.createElement("url");
            urlElement.appendChild(doc.createTextNode(url.toString()));
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this.getParent(), ex.getMessage(), "URL error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (checkBoxResize.isSelected()) {
            try {
                int width = Integer.parseInt(fieldWidth.getText());
                int height = Integer.parseInt(fieldHeight.getText());
                if (width <= 0 || height <= 0) {
                    throw new NumberFormatException("Positive dimensions are required");
                }
                Element widthElement = doc.createElement("width");
                widthElement.appendChild(doc.createTextNode("" + width));
                Element heightElement = doc.createElement("height");
                heightElement.appendChild(doc.createTextNode("" + height));
                element.appendChild(widthElement);
                element.appendChild(heightElement);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.getParent(), ex.getMessage(), "Dimensions error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        element.appendChild(urlElement);
        return true;

    }

    @Override
    public void loadElement(Element element) {
        fieldURL.setText(getAttribute(element, "url") == null ? "URL" : getAttribute(element, "url"));
        if (getAttribute(element, "width") != null && getAttribute(element, "height") != null) {
            checkBoxResize.setSelected(true);
            fieldWidth.setEnabled(true);
            fieldHeight.setEnabled(true);
            fieldWidth.setText(getAttribute(element, "width") == null ? "WIDTH" : getAttribute(element, "width"));
            fieldHeight.setText(getAttribute(element, "height") == null ? "HEIGHT" : getAttribute(element, "height"));
        }
    }
}