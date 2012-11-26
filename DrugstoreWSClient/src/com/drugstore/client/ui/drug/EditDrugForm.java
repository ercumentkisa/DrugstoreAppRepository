/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditDrugForm.java
 *
 * Created on 24.Ara.2011, 21:47:30
 */
package com.drugstore.client.ui.drug;

import com.drugstore.client.DrugstoreWSClient;
import com.drugstore.client.util.DrugstoreClientUtil;
import com.drugstore.client.ws.Drug;
import com.drugstore.client.ws.Manufacturer;
import com.drugstore.client.ws.PrescriptionType;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author ekisa
 */
public class EditDrugForm extends javax.swing.JDialog {

    private List<Drug> alternativesList;
    private Drug data;
    
    /** Creates new form EditDrugForm */
    public EditDrugForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initData();
        DrugstoreClientUtil.centerComponent(this);
    }

    public EditDrugForm() {
    }
    
    public EditDrugForm(DrugListForm parent, boolean modal) {
        this(DrugstoreClientUtil.getTopFrame(), modal);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        drugListRenderer1 = new com.drugstore.client.ui.renderer.DrugListRenderer();
        prescriptionTypeListRenderer1 = new com.drugstore.client.ui.renderer.PrescriptionTypeListRenderer();
        manufacturerListRenderer1 = new com.drugstore.client.ui.renderer.ManufacturerListRenderer();
        jScrollPane2 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        codeTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numberOfPillsTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        owningCostTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        sellingCostTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        prescriptionTypeCmb = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        alternativeCombo = new javax.swing.JComboBox();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        manufacturerCmbBox = new javax.swing.JComboBox();
        commandPanel = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Drug");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 5, 20);
        mainPanel.add(jLabel1, gridBagConstraints);

        nameTF.setPreferredSize(new java.awt.Dimension(200, 30));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.name}"), nameTF, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        mainPanel.add(nameTF, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Code :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel2, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.code}"), codeTF, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(codeTF, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Number of Pills :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel3, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.numberOfPills}"), numberOfPillsTF, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(numberOfPillsTF, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Owning Cost :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel4, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.owningCost}"), owningCostTF, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(owningCostTF, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Selling Cost :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel5, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.sellingCost}"), sellingCostTF, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(sellingCostTF, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Prescription Type :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel6, gridBagConstraints);

        prescriptionTypeCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        prescriptionTypeCmb.setPreferredSize(new java.awt.Dimension(178, 30));
        prescriptionTypeCmb.setRenderer(prescriptionTypeListRenderer1);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.prescriptionType}"), prescriptionTypeCmb, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        prescriptionTypeCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescriptionTypeCmbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(prescriptionTypeCmb, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Alternative :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel7, gridBagConstraints);

        alternativeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        alternativeCombo.setPreferredSize(new java.awt.Dimension(200, 30));
        alternativeCombo.setRenderer(drugListRenderer1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 55);
        mainPanel.add(alternativeCombo, gridBagConstraints);

        addBtn.setText(" + ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(addBtn, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 130));

        jTable1.setFillsViewportHeight(true);
        jTable1.setPreferredSize(new java.awt.Dimension(300, 300));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${alternativesList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${code}"));
        columnBinding.setColumnName("Code");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        mainPanel.add(jScrollPane1, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Manufacturer :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 20);
        mainPanel.add(jLabel8, gridBagConstraints);

        manufacturerCmbBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        manufacturerCmbBox.setRenderer(manufacturerListRenderer1);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${data.manufacturer}"), manufacturerCmbBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        mainPanel.add(manufacturerCmbBox, gridBagConstraints);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        commandPanel.add(cancelBtn);

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        commandPanel.add(saveBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 40);
        mainPanel.add(commandPanel, gridBagConstraints);

        jScrollPane2.setViewportView(mainPanel);

        getContentPane().add(jScrollPane2);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().persistDrug(data);
        firePropertyChange("drugList", null, data);
        this.dispose();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void prescriptionTypeCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescriptionTypeCmbActionPerformed
        final DefaultComboBoxModel model = (DefaultComboBoxModel) alternativeCombo.getModel();
        model.removeAllElements();
        if (data == null) {
            return;
        }
        List<Drug> allDrugs = DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().listDrugs();
        for (Iterator<Drug> iterator = allDrugs.iterator(); iterator.hasNext();) {
            Drug curr = iterator.next();
            if (curr.getPrescriptionType().equals(prescriptionTypeCmb.getSelectedItem())) {
                model.addElement(curr);
            }
        }
    }//GEN-LAST:event_prescriptionTypeCmbActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        Drug selectedDrug = (Drug) alternativeCombo.getSelectedItem();
        if(selectedDrug != null){
            alternativesList.add(selectedDrug);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox alternativeCombo;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField codeTF;
    private javax.swing.JPanel commandPanel;
    private com.drugstore.client.ui.renderer.DrugListRenderer drugListRenderer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox manufacturerCmbBox;
    private com.drugstore.client.ui.renderer.ManufacturerListRenderer manufacturerListRenderer1;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTextField numberOfPillsTF;
    private javax.swing.JTextField owningCostTF;
    private javax.swing.JComboBox prescriptionTypeCmb;
    private com.drugstore.client.ui.renderer.PrescriptionTypeListRenderer prescriptionTypeListRenderer1;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField sellingCostTF;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void initData() {
        fetchPrescriptionTypeListFromServer();
        fetchManufacturerListFromServer();
        cleanAlternativesList();
        this.firePropertyChange("data", null, data);
    }

    public void fetchPrescriptionTypeListFromServer() {
        List<PrescriptionType> prescriptionTypes = DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().listPrescriptionTypes();
        final DefaultComboBoxModel model = (DefaultComboBoxModel) prescriptionTypeCmb.getModel();
        model.removeAllElements();
        for (Iterator<PrescriptionType> iterator = prescriptionTypes.iterator(); iterator.hasNext();) {
            model.addElement(iterator.next());
        }
    }

    public void fetchManufacturerListFromServer() {
        List<Manufacturer> manufacturerList = DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().listManufacturers();
        final DefaultComboBoxModel model = (DefaultComboBoxModel) manufacturerCmbBox.getModel();
        model.removeAllElements();
        for (Iterator<Manufacturer> iterator = manufacturerList.iterator(); iterator.hasNext();) {
            model.addElement(iterator.next());
        }
    }
    public void cleanAlternativesList() {
        final DefaultComboBoxModel model = (DefaultComboBoxModel) alternativeCombo.getModel();
        model.removeAllElements();
        alternativeCombo.setSelectedIndex(-1);
    }
    
    public void newDrug() {
        data = new Drug();
        alternativesList = ObservableCollections.observableList(data.getAlternatives());
        this.firePropertyChange("data", null, data);
        this.firePropertyChange("alternativesList", null, alternativesList);
    }
    public void editDrug(Drug drug) {
        data = DrugstoreWSClient.getInstance().getWebService().getDrugstoreWSPort().findDrug(drug.getId());
        alternativesList = ObservableCollections.observableList(data.getAlternatives());
        this.firePropertyChange("data", null, drug);
        this.firePropertyChange("alternativesList", null, alternativesList);
        
    }

    public Drug getData() {
        return data;
    }

    public void setData(Drug data) {
        this.data = data;
    }

    public List<Drug> getAlternativesList() {
        return alternativesList;
    }

    public void setAlternativesList(List<Drug> alternativesList) {
        this.alternativesList = alternativesList;
    }
}