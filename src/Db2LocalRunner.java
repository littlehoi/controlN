

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;



public class Db2LocalRunner {

 static Connection  conn;
 String[] raymondRemovetables={"acs_adj_aud","acs_adj_mast_d","acs_advpay_wk","acs_agt_bal","acs_agt_pay","acs_agtrec","acs_agtrec_rate","acs_banc_bonus","acs_banc_pb_hist","acs_banc_pdsum","acs_benf_reg1","acs_bonus_rate","acs_col_fee","acs_col_fee_pr","acs_col_fee_wk1","acs_col_fee_wk2","acs_com_banc","acs_com_banc_acct","acs_com_banc_bc_wk","acs_com_banc_grp","acs_com_banc_per","acs_com_banc_rate","acs_com_banc_sum","acs_com_scb","acs_com_wh","acs_com_wh_ape","acs_com_wk","acs_comm_brk","acs_comm_hist","acs_comm_mast","acs_comp_sum","acs_comp_sum_arc","acs_devall","acs_devall_rate","acs_dpay_hist","acs_dpay_hist_pr","acs_dpay_hist_wk1","acs_dpay_hist_wk2","acs_exprmb","acs_exprmb_dtl","acs_exprmb_rate","acs_fl_list_pr","acs_fl_list_wk1","acs_fl_list_wk2","acs_follow_list","acs_gl_acct","acs_gl_optcd","acs_gl_pgm","acs_newca","acs_newcam_tar","acs_newcay_rate","acs_offsecall","acs_oig_pay","acs_or_rhier","acs_orcom","acs_orcom_7tier","acs_orcom_clawback","acs_pay_parm","acs_pay_sum_rpt","acs_pdd_ape_adj","acs_pdd_bonus_sum","acs_pdd_case_rmth","acs_pdd_case_smth","acs_pdd_com_sum","acs_pdd_hdr","acs_pdd_ipb","acs_pdd_ipb_dtl","acs_pdd_mb","acs_pdd_pb","acs_pdd_pb_cas","acs_pdd_pb_com","acs_pdd_pb_sta","acs_pdd_pol","acs_pdd_qb","acs_pdd_rep_dtl","acs_pdd_sched_mast","acs_pdd_spb","acs_pdd_spb_dtl","acs_pdd_split","acs_pdpo_rule","acs_perbon","acs_perbon_rate","acs_permod","acs_permod_rate","acs_probk","acs_probk_rate","acs_probn","acs_probn_d","acs_probn_rate","acs_profsc","acs_qsbn","acs_receipt_ctl","acs_receipt_prt_dtl","acs_receipt_prt_hist","acs_ref_cd_pr","acs_ref_cd_wk1","acs_ref_cd_wk2","acs_ref_fee","acs_ref_fee_adj","acs_ref_fee_pr","acs_ref_fee_tf","acs_ref_fee_tf_pr","acs_ref_fee_tf_wk","acs_ref_fee_wk","acs_reg_rule","acs_reg6_dtl","acs_rule","acs_sched_mast","acs_sched_mast_d","acs_sched_mast_pr","acs_sched_mast_wk","acs_sched_sub","acs_spall","acs_spall_dtl","acs_spall_rate","acs_sprec","acs_sprec_dtl","acs_sprec_rate","acs_stmt_sfmt","acs_sua_or_wk","acs_sua_stmt_wk","acs_sua_sum","acs_sua_txn","acs_sua_txn_pr","acs_sua_txn_wk1","acs_sua_txn_wk2","acs_ugbon","acs_ugbon_rate","acs_upl_ctrl","acs_upl_def_det","acs_upl_def_hdr","acs_upl_mast","acs_y2k1_agt","acs_y2k1_ereq","acs_y2k1_qreq","acs_y2kbn","add_easiid_mast_pr","add_easiid_mast_wk","add_easiid_mst_his","add_easiid_mst_log","aff_scbdisc","afh_apr_agt_mfin_hdr","afn_agt_bal","afn_income_wk","afn_mth_def","afn_mth_fin","afn_mth_fin_hist","afn_paymt_ratio","afn_personal_inc","afn_rank_api","afn_unit_limit","afn_valid_formula","afn_valid_txn","afp_apr_agt_mfin_pratio","afv_apr_agt_mfin_valid","agd_apr_agt_aux_rcd","age_apr_cdb_agent_extmast","agm_apr_cdb_agent_mast","ago_apr_cdb_agent_offi","agr_apr_agt_aux_rec","ags_apr_cdb_agent_submast","agt_addi_rel","agt_aux_rec_wk","agt_aux_rec_wk_pr","agt_aux_rec2","agt_aux_rec2_pr","agt_aux_rec2_wk","agt_award_adj","agt_award_adj_pr","agt_award_adj_wk","agt_award_result","agt_award_setup","agt_awdcode_result","agt_bank_hist","agt_bank_hist_pr","agt_bank_hist_wk","agt_benf","agt_benf_hist","agt_benf_pay","agt_benf_pr","agt_benf_sch_pr","agt_benf_wk","agt_cd_tbl","agt_cies","agt_cies_pr","agt_cies_wk","agt_con_maint","agt_contract","agt_contract_aux","agt_contract_pr","agt_contract_wk1","agt_contract_wk2","agt_cpd_cred","agt_date_cntr","agt_earn_cli_wk","agt_earn_cli_wk_hist","agt_earn_wk","agt_education","agt_education_pr","agt_education_wk","agt_efax_pool","agt_efax_pool_pr","agt_efax_pool_wk","agt_emp_hist","agt_exam_result","agt_exam_rlt_pr","agt_exam_rlt_wk","agt_frole_asgn_pr","agt_frole_asgn_wk1","agt_frole_asgn_wk2","agt_grp_prod","agt_headcount","agt_icsr_ratio","agt_kpi_fig","agt_kpi_fig_hist","agt_kpi_ftr","agt_license_hist","agt_license_reg","agt_license_reg_hist","agt_license_reg_remark","agt_license_reg_remark_hist","agt_m_at_midyr","agt_mail_cli_out","agt_mail_out","agt_mail_tplt","agt_mail_tplt_ele","agt_mdrt_adj","agt_mdrt_adj_pr","agt_mdrt_adj_wk","agt_mdrt_hier","agt_mdrt_info","agt_mfin_hdr","agt_mfin_pratio","agt_mfin_valid","agt_mgt_adj","agt_mgt_adj_pr","agt_mgt_adj_wk","agt_mkt_award","agt_mkt_award_pr","agt_mkt_award_wk","agt_mpf_afyp","agt_mvmt_hist","agt_mvmt_regstr","agt_mvmt_sta","agt_new_afyp","agt_nmcard_setup","agt_off_ac_tr_pr","agt_off_ac_tr_wk1","agt_off_ac_tr_wk2","agt_offi_ac_tr","agt_or_hier","agt_or_rate_tbl","agt_orp_comm_hold","agt_orphan_aes_asgn","agt_orphan_benf","agt_orphan_ctl","agt_orphan_elig_info","agt_orphan_elig_info_hist","agt_orphan_elig_list","agt_orphan_elig_list_hist","agt_orphan_icsr","agt_orphan_pol_list","agt_orphan_pol_wk1","agt_orphan_pol_wk2","agt_orphan_sp_hdr","agt_orphan_sp_hdr_trail","agt_para_grp","agt_para_setup_chk","agt_para_setup_pr","agt_para_setup_wk","agt_pay_info_hist","agt_payroll_wh","agt_payroll_wh_pr","agt_payroll_wh_wk","agt_pfm_rvw_rpt","agt_pfm_rvw_wk1","agt_pfm_rvw_wk2","agt_pol_wk1_pr","agt_pre_out","agt_print_supp","agt_print_supp_pr","agt_print_supp_wk","agt_prod","agt_prof","agt_prof_pr","agt_prof_wk","agt_promot_rule","agt_pru_cs_cred","agt_rel","agt_rhier_aud","agt_rm_fol_itm","agt_rm_fol_itm_pr","agt_rm_fol_itm_wk1","agt_rm_fol_itm_wk2","agt_rpt_hier_pr","agt_rpt_hier_wk1","agt_rpt_hier_wk2","agt_seat_allo","agt_seat_allo_pr","agt_seat_allo_wk1","agt_seat_allo_wk2","agt_sp_asgn_wk1","agt_sp_asgn_wk2","agt_sp_reject","agt_sp_wk1_pr","agt_sys_tbl","agt_tax_adj","agt_tax_adj_pr","agt_tax_adj_wk","agt_team_cv","agt_term_info","agt_term_info_hist","agt_title_wk","agt_tra_cs_cred","agt_unu_fund_hdr","agt_unu_fund_txn","agt_unu_fund_txn_pr","agt_unu_fund_wk_pr","agt_unu_fund_wk1","agt_unu_fund_wk2","agt_vest_benf","agt_vest_benf_pr","agt_vest_benf_wk","ap_nonagt_mast","apd_ap_rpt_hdr","apd_benefit","apd_benf_txn","apd_bgt_fin","apd_bgt_fin_pr","apd_bgt_fin_wk","apd_budget","apd_budget_pr","apd_budget_wk","apd_case_cnt","apd_cls_mth_pr","apd_cls_mth_wk","apd_dir_recu_unit","apd_dirrec_wk1","apd_dmtm_ape","apd_dmtm_ape_pr","apd_dmtm_ape_prod","apd_dmtm_ape_wk","apd_dmtm_cvfi","apd_dmtm_mpw_pr","apd_dmtm_mpw_wk","apd_dpf_ape_brk","apd_dpf_grp","apd_dpf_kpi","apd_dpf_kpi_brk","apd_dpf_sum","apd_fcstw_ape_pr","apd_fcstw_ape_wk","apd_fin_adj","apd_fin_adj_pr","apd_fin_adj_wk","apd_fin_anh","apd_fin_anh_pr","apd_fin_anh_wk","apd_fin_benf","apd_fin_benf_dt","apd_fin_benf_txn","apd_fin_benf_txn_hist","apd_fin_cls_mth","apd_fin_fcst","apd_fin_fcst_pr","apd_fin_fcst_wk","apd_fin_mpw","apd_fin_nbp_benf","apd_fin_nbp_mrg_pr","apd_fin_nbp_mrg_wk","apd_fin_prod_brk","apd_fin_prod_grp","apd_fin_recon","apd_fin_zone_txn","apd_group","apd_group_prufig","apd_grp_nbsub_day","apd_grp_nbsub_mth","apd_grprec_wk1","apd_mpw_src","apd_mpw_src_pr","apd_mpw_src_wk","apd_nb_submfig","apd_pdd_benf_sum_day","apd_pdd_benf_sum_mth","apd_pdd_benf_txn_day","apd_pdd_benf_txn_mth","apd_pdd_dmtm_ape_day","apd_pdd_mpw_pr","apd_pdd_mpw_wk","apd_person_prufig","apd_personal","apd_personal_eb","apd_personal_ext","apd_personal_sum","apd_prod_adj_pr","apd_prod_adj_wk","apd_prod_dtl_pr","apd_prod_dtl_wk","apd_prod_grp_pr","apd_prod_grp_wk","apd_pru_ap","apd_pru_control","apd_pru_division","apd_pru_ss","apd_pru_ts","apd_psn_nbsub_day","apd_psn_nbsub_mth","apd_qtr_cntrmn","apd_rpt_chan_pr","apd_rpt_chan_wk","apd_sales_rpt","apd_sub_personal","apd_sur_mtd","apd_sur_pol","apd_team_pr","apd_team_wk1","apd_team_wk2","apd_unit","apd_untrec_wk1","app_club_qual","apr_chk_hkfi_pru","apr_hkfi_pru_email","apr_hkfi_pru_pr","apr_hkfi_pru_wk","apr_mail_out","apr_mail_tplt","apr_mail_tplt_ele","aps_agent","aps_agent_13","aps_agent_19","aps_agt_ref_13","aps_agt_ref_19","aps_benf_adj","aps_benf_exp","aps_benf_exp_13","aps_benf_exp_19","aps_benf_hist","aps_benf_wk","aps_benf_wk_13","aps_benf_wk_19","aps_dir_recru","aps_drecru_13","aps_drecru_19","aps_group","aps_group_13","aps_group_19","aps_grp_wk","aps_grp_wk_hist","aps_pol_benf","aps_rpt_def_dtl","aps_rpt_def_hdr","aps_rpt_grp","aps_unit","aps_unit_13","aps_unit_19","arg_apr_cdb_agt_reg","asr_apr_agt_hkfi_sr","aux_comp_para","bt_protect","cdb_agent_address","cdb_agent_contact","cdb_agent_mast_pr","cdb_agent_mast_wk1","cdb_agent_mast_wk2","cdb_agent_offi","cdb_agt_reg_hist","cdb_agt_reg_pr","cdb_agt_reg_wk1","cdb_agt_reg_wk2","cdb_agt_submst_pr","cdb_agt_submst_wk1","cdb_agt_submst_wk2","cdb_agt_submst2_pr","cdb_agt_submst2_wk","cdb_option_pr","cdb_option_wk1","cdb_option_wk2","cdb_seat","cdb_seat_pr","cdb_seat_wk","com_banc_grp_pr","com_banc_grp_wk","consol_agent_hist","consol_statem_dtl","consol_statem_sum","cpd_exp_data","cpd_insurer_pin","cpd_insurer_pr","cpd_insurer_wk1","cpd_insurer_wk2","cpd_policy","cpd_policy_pr","cpd_policy_wk","cpd_rpt_data","cpd_upd_wk1","cpd_upd_wk1_pr","cpd_upd_wk2","crl_cmtr_rpt_log","cust_orphan_segment","custno_nbs_kpi","dyna_fld_mast","dyna_fld_submast","dyna_tbl_mast","eb_prod","excel_upd_setup","gb_prod","gl_coa_map01_pr","gl_coa_map01_wk","gl_coa_map02_pr","gl_coa_map02_wk","gl_new_coa_ctl","gl_new_coa_map01","gl_new_coa_map02","gl_tran_detail","lbl_ctrl_log","lbl_extr_file","lbl_extr_hdr","lbl_extr_pfile","lbl_prt_order","lg_cse_rpt_zn_dtl","m5380_excep_rpt","m5380_gl_no","m5380_wrk_rpt","mkt_adj_rec","mkt_ah_prod","mkt_award","mkt_cda","mkt_contest","mkt_contest_result","mkt_dir_tm_hier_wk","mkt_dn_hier_new","mkt_dn_hier_wk","mkt_g5_dn_hier_wk","mkt_grp","mkt_hoinan_ti_g1l2","mkt_hoinan_ti_g1l3","mkt_hoinan_ti_per","mkt_iqa","mkt_mdrt_prod_wk","mkt_new_recru","mkt_nma_maa","mkt_non_vip_pr","mkt_non_vip_wk1","mkt_non_vip_wk2","mkt_personal","mkt_prod_rec","mkt_rookies_club","mkt_split","mkt_vip_pr","mkt_vip_wk1","mkt_vip_wk2","mod_contact_info","mvmt_sched_dtl","mvmt_sched_hdr","nb_report_dtl","nb_report_log","nb_report_misc","nb_report_raw","nb_report_rmk","nb_report_rmk_pr","nb_report_rmk_wk","nb_report_sum","nb_report_txn","nb_report_zone_dtl","new_sun_ana_map_pr","new_sun_ana_map_wk","pae_prun_acs_exprmb","pas_prun_acs_spall","pay_ctl_rpt","pdd_bonus_sum_pr","pdd_bonus_sum_wk","pdd_brk_class","pdd_brk_class_wk","pdd_brk_extmast","pdd_brk_mis","pdd_brk_reg_pr","pdd_brk_reg_wk","pdd_brk_staff_reg","pdd_cfi_ntu","pdd_chg_ref_cd","pdd_chg_ref_cd_pr","pdd_chg_ref_cd_wk","pdd_com_sum_log","pdd_com_sum_pr","pdd_com_sum_wk","pdd_dsm_brh_setup","pdd_dsm_brh_setup_log","pdd_dsm_bsetup_pr","pdd_dsm_bsetup_wk","pdd_fsm_brh_setup","pdd_fsm_bsetup_pr","pdd_fsm_bsetup_wk","pdd_geid_mast","pdd_geid_mast_hist","pdd_geid_wk","pdd_hdr_log","pdd_hdr_pr","pdd_hdr_wk","pdd_hogan_list","pdd_hogan_list_pr","pdd_hogan_list_wk1","pdd_hogan_list_wk2","pdd_io_reg_pr","pdd_io_reg_wk","pdd_mgt_trgt","pdd_mgt_trgt_log","pdd_mgt_trgt_pr","pdd_mgt_trgt_wk","pdd_mth_bonus","pdd_pb_min_case","pdd_prem_chg","pdd_rep_dtl_wk1","pdd_rep_dtl_wk1_pr","pdd_rep_dtl_wk2","pdd_rep_expt","pdd_rep_rec","pdd_rep_sum","pdd_revenue_rate","pdd_rpt_para_wk","pdd_scb_dereg_pr","pdd_scb_dereg_wk","pdd_scb_io_reg","pdd_scb_reg_pr","pdd_scb_reg_wk","pdd_scb_staff_drg","pdd_scb_staff_reg","pdd_sched_mast_log","pdd_sched_mast_pr","pdd_sched_mast_wk","pdd_valid_pol_wk","pdd_wtd_cycle","ped_prun_acs_exprmb_dtl","pf_income_sum","pf_income_wk","pf_orso_wk","pgm_menu_ctl","pol_add_info_pr","pol_add_info_wk1","pol_add_info_wk2","pol_affinity_exp","pol_ass_rule","pol_benf_api_txn","pol_benf_fycryc","pol_benf_mvmt_log","pol_benf_mvmt_pr","pol_benf_mvmt_wk1","pol_benf_mvmt_wk2","pol_benf_per_hist","pol_benf_rsa_pr","pol_benf_rsa_wk1","pol_benf_rsa_wk2","pol_benf_txn_mt","pol_benf_txn_mt_pr","pol_benf_txn_mt_wk","pol_brk_fycsyc","pol_prem_bas_txn","pol_prem_scb","pp_agt_folder","pre_prop_ape","prod_cred_limit","prod_cred_policy","prod_credit_top_5","profit_comm","prugeneb_agt_hier","psd_prun_acs_spall_dtl","recu_bonus_dtl","recu_bonus_hdr","ref_rate_grp","reg_rule","reg6_upd_wk1","reg6_upd_wk1_pr","reg6_upd_wk2","report_auto_ctrl","revenue_rate_pr","revenue_rate_wk","scb_act_dt_chg","scb_act_dt_chg_pr","scb_act_dt_chg_wk","scb_actdt_chg_hist","scb_bal_chk","sfa_agt_cup_item","sfa_agt_hier","sfa_agt_hier_rule","sfa_agt_hier_rule_map","sfa_agt_hier_rule_val","sfa_agt_ldm4365_tmp","sfa_agt_mcui_pr","sfa_agt_mcui_wk1","sfa_agt_mcui_wk2","sfa_cdb_arg_pr","sfa_cdb_arg_wk1","sfa_cdb_arg_wk2","sfa_chan_tgt","sfa_chan_tgt_pr","sfa_chan_tgt_wk","sfa_cui_padj","sfa_cui_padj_pr","sfa_cui_padj_wk1","sfa_cui_padj_wk2","sfa_ddd","sfa_email_rec","sfa_hash_pass","sfa_housekeep_config","sfa_per_tgt","sfa_per_tgt_pr","sfa_per_tgt_wk","sfa_schedule","sfa_user_config_pr","sfa_user_config_wk","sys_workday_info","temp_cdb_agent11","temp_cdb_agent12","temp_cdb_agent13","temp_cdb_agent14","temp_cdb_agent201","temp_cdb_agent202","temp_cdb_agent203","temp_cdb_agent204","temp_cdb_agent205","temp_cdb_agent206","temp_cdb_agent207","temp_cdb_agent208","temp_cdb_agent209","temp_cdb_agent21","temp_cdb_agent31","temp_cdb_agent41","temp_cdb_agent51","temp_cdb_agent61","temp_cdb_agent71","three_tier_bonus","tmo_mast","tmo_mast_pr","tmo_mast_wk","wk_apd_sur_pol","wk_mkt_dn_hier","wk_mkt_dnline","wk_mkt_manager","wk_mkt_tm_dn_hier","workday_info_pr","workday_info_wk","prop_mvmt_hdr_hist","pol_mvmt_hdr_hist","pol_pbasic_mvmt","pol_pbenf_mvmt"};
 String[] bensRemovetables={"agt_aux_rec","aes_acc_ath","cdb_agent_mast","aes_acc_ath","agt_aud_log","agt_aux_rcd","cdb_agent_mast","cdb_agent_submast","aes_acc_ath","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","pol_benf_per_hdr","apd_group","cdb_agent_mast","apd_group","aps_group","cdb_agent_mast","acs_comp_sum","acs_stmt_sfmt","cdb_agent_extmast","cdb_agent_mast","aps_benf_exp","aps_benf_exp_13","aps_benf_exp_19","aps_benf_hist","aps_pol_benf","cdb_agent_mast","cdb_agt_reg","agt_earn_wk","acs_adj_aud","bf_pol_ctl","bf_pol_options","bf_prop_options","cdb_agent_mast","agt_asc_tbl","apd_unit","agt_asc_tbl","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","acs_rate_tbl","agt_para_setup","cdb_agent_mast","pol_benf_txn_mt","apd_case_cnt","cdb_agent_mast","cdb_agt_reg","agt_education","agt_nmcard_setup","agt_para_setup","agt_adbu_setup","agt_aux_rcd","agt_aux_rec","agt_bank_hist","agt_desgn","agt_mfin_hdr","agt_para_setup","agt_rpt_hier","agt_term_info","apd_team","aux_comp_code","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","cdb_agent_submast2","aes_acc_ath","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","agt_benf","agt_benf_sch_pr","agt_para_setup","cdb_agent_submast","agt_para_setup","agt_prof","agt_aux_rec","agt_para_setup","cdb_agent_submast","cdb_agent_mast","cdb_agent_mast","aes_acc_ath","agt_aux_rec","agt_para_setup","cdb_agent_mast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","agt_aux_rec","agt_tra_cs","agt_tra_cs_cred","cdb_agent_mast","cdb_agent_submast","cdb_agt_reg","aes_agt_rhier","agt_aux_rec","agt_orphan_icsr","cdb_agent_mast","cdb_agent_mast","add_easiid_mast","agt_desgn","cdb_agent_mast","agt_mkt_award","agt_nmcard_setup","mkt_award","agt_tra_cs","agt_tra_cs_cred","cdb_agt_reg","cdb_agt_reg","cdb_agt_reg","acs_para_setup","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","apd_case_cnt","prod_credit","agt_aux_rec","cdb_agent_mast","cdb_agent_submast","agt_orphan_icsr","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","agt_adbu_setup","agt_aux_rec","apd_group","apd_group_prufig","apd_grp_nbsub_day","apd_person_prufig","apd_psn_nbsub_day","cdb_agent_mast","cdb_agent_submast","agt_icsr_ratio","agt_orphan_policy","agt_prod","apd_personal","aps_agent","aps_agent_19","cdb_agent_mast","agt_aux_rec","cdb_agent_submast","cdb_agt_reg","cdb_agent_mast","cdb_agent_mast","agt_tra_cs","agt_tra_cs_cred","apd_group","aps_group","agt_rpt_hier","cdb_agent_mast","pol_benf_per_hdr","cdb_agent_mast","cdb_agent_mast","acs_para_setup","cdb_agent_mast","cdb_agent_mast","acs_para_setup","cdb_agent_mast","aes_acc_ath","agt_asc_tbl","agt_sta_rs","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","cdb_agent_mast","agt_mdrt_hier","agt_mdrt_info","cdb_agent_mast","agt_mdrt_hier","agt_mdrt_hier","agt_mdrt_info","cdb_agent_mast","agt_mdrt_hier","acs_sched_mast","cdb_agent_mast","cdb_agent_mast","cdb_agent_submast","pol_benf_per_hdr","cdb_agent_mast","cdb_agent_submast","pol_benf_per_hdr","acs_adj_mast","acs_sched_mast","aux_comp_code","aes_acc_ath","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_mast","pol_benf_per_hdr","cdb_agent_mast","pol_benf_per_hdr","pol_benf_per_hdr","cdb_agent_mast","cdb_agent_mast","apd_case_cnt","apd_personal","aps_agent","aps_agent_13","cdb_agent_mast","prod_credit","aes_agt_rhier","cdb_agent_mast","apd_dir_recu_unit","apd_unit","cdb_agent_mast","apd_dir_recu_unit","apd_unit","aps_dir_recru","aps_unit","cdb_agent_mast","agt_rpt_hier","agt_rpt_hier","cdb_agent_submast","agt_rpt_hier","agt_rpt_hier","agt_rpt_hier","cdb_agent_mast","pol_benf_per_hdr","pol_benf_per_hdr","cdb_agent_extmast","cdb_agent_mast","acs_follow_list","acs_remark","acs_sua_txn","acs_follow_list","acs_sua_stmt_wk","acs_sua_txn","cdb_agent_mast","agt_desgn","cdb_agt_ext","agt_desgn","cdb_agt_ext","add_easiid_mast","cdb_agent_mast","ap_nonagt_mast","cdb_agent_mast","ap_nonagt_mast","cdb_agent_mast","cdb_agent_mast","acs_adj_mast","afn_personal_inc","agt_mfin_hdr","agt_mfin_valid","agt_para_setup","apd_personal","agt_adbu_setup","agt_ctl_tbl","agt_desgn","agt_mvmt_log","agt_para_tbl","agt_rpt_hier","cdb_agent_mast","cdb_agent_submast","cdb_agent_extmast","cdb_agent_mast","agt_desgn","agt_rpt_hier","agt_desgn","agt_rpt_hier","agt_rpt_hier","cdb_agent_mast","cdb_agent_submast","agt_rpt_hier","agt_desgn","agt_desgn","acs_agt_bal","acs_sched_mast","agt_mfin_hdr","cdb_agent_submast","cdb_agent_submast","agt_aux_rcd","agt_mfin_hdr","agt_mfin_valid","cdb_agent_mast","cdb_agent_submast","acs_adj_mast","acs_sched_mast","agt_mfin_hdr","afn_mth_fin","agt_mfin_hdr","agt_mfin_pratio","agt_mfin_valid","apd_case_cnt","apd_personal","agt_aux_rcd","agt_adbu_setup","cdb_agent_mast","acs_bonus_rate","afn_mth_fin","agt_mfin_hdr","apd_personal","cdb_agent_mast","cdb_agent_submast","agt_para_setup","aps_agent","acs_rule","agt_para_setup","agt_desgn","agt_mfin_hdr","agt_para_setup","cdb_agent_mast","agt_mfin_hdr","agt_mfin_hdr","agt_mfin_valid","agt_para_setup","acs_or_rhier","apd_personal","agt_asc_tbl","agt_ctl_tbl","agt_mvmt_log","agt_para_tbl","agt_rpt_hier","cdb_agent_mast","cdb_agent_submast","agt_rpt_hier","agt_rpt_hier","agt_rpt_hier","agt_mvmt_log","agt_rpt_hier","cdb_agent_mast","agt_adbu_setup","agt_aux_rec","agt_para_setup","apd_team","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","agt_mfin_valid","agt_rpt_hier","cdb_agent_mast","agt_rpt_hier","agt_sta_hist","cdb_agent_mast","agt_adbu_setup","apd_grp_nbsub_day","apd_grp_nbsub_mth","apd_psn_nbsub_day","apd_psn_nbsub_mth","cdb_agent_mast","agt_adbu_setup","apd_grp_nbsub_mth","apd_psn_nbsub_mth","cdb_agent_mast","agt_adbu_setup","apd_grp_nbsub_day","apd_psn_nbsub_day","apd_psn_nbsub_mth","cdb_agent_mast","agt_adbu_setup","apd_psn_nbsub_mth","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","cdb_agent_mast","pol_benf_txn_mt","cdb_agent_mast","aes_acc_ath","add_easiid_mast","aes_acc_ath","add_easiid_mast","aes_acc_ath","cdb_agent_mast","agt_para_setup","aes_acc_ath","cdb_agent_mast","aes_acc_ath","agt_aux_rec","cdb_agent_mast","aes_acc_ath","agt_sta_rs","ap_nonagt_mast","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","aes_acc_ath","cdb_agent_extmast","cdb_agent_mast","agt_prod","apd_personal","aps_agent","aps_agent_13","cdb_agent_mast","mvmt_sched_hdr","apd_case_cnt","apd_personal","cdb_agent_mast","cdb_agent_mast","apd_case_cnt","prod_credit","ref_rate_tbl","aes_acc_ath","aes_acc_ath","aes_acc_ath","cdb_agent_mast","agt_aux_rec","agt_desgn","agt_orphan_pol_wk1","agt_orphan_policy","agt_para_setup","cdb_agent_mast","aes_acc_ath","aes_acc_ath","aes_acc_ath","cdb_agt_reg","cdb_agent_extmast","cdb_agent_mast","agt_adbu_setup","agt_ctl_tbl","agt_rpt_hier","apd_team","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","cdb_agt_ext","cdb_agent_mast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","agt_rpt_hier","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agt_reg","cdb_agent_extmast","cdb_agent_mast","cdb_agt_reg","agt_aux_rec","cdb_agent_mast","cdb_agent_submast","cdb_agent_mast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","agt_rpt_hier","cdb_agent_submast","cdb_agent_mast","cdb_agt_reg","cdb_agent_mast","pol_benf_per_hdr","cdb_agent_mast","agt_asc_tbl","cdb_agent_mast","cdb_agent_mast","apd_case_cnt","apd_group","cdb_agent_mast","apd_group","cdb_agent_mast","apd_group","aps_group","cdb_agent_mast","agt_aux_rcd","agt_bank_hist","agt_rpt_hier","cdb_agent_mast","cdb_agent_submast","apd_group","cdb_agent_mast","apd_group","aps_group","cdb_agent_mast","agt_adbu_setup","apd_grp_nbsub_day","apd_psn_nbsub_day","cdb_agent_mast","agt_adbu_setup","apd_grp_nbsub_day","apd_psn_nbsub_day","cdb_agent_mast","agt_aux_rec","cdb_agent_submast","acs_para_setup","add_easiid_mast","agt_para_setup","cdb_agent_mast","sfa_sales_chan","agt_rpt_hier","agt_sta_hist","cdb_agent_mast","cdb_agent_submast","cdb_agent_mast","apd_grp_nbsub_day","apd_psn_nbsub_day","cdb_agent_mast","agt_mvmt_log","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","apd_personal","apd_personal_sum","cdb_agent_mast","apd_personal","apd_personal_sum","cdb_agent_mast","aes_acc_ath","agt_aux_rec","cdb_agent_submast","acs_para_setup","cdb_agent_mast","pol_benf_per_hdr","cdb_agent_mast","pol_benf_per_hdr","cdb_agent_mast","pol_benf_per_hdr","mod_contact_info","agt_desgn","agt_para_setup","cdb_agent_mast","cdb_agent_mast","apd_personal","apd_personal_sum","cdb_agent_mast","apd_personal","apd_personal_sum","cdb_agent_mast","acs_para_setup","cdb_agent_mast","agt_mvmt_log","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","aes_acc_ath","aes_acc_ath","pol_benf_txn_mt","apd_unit","cdb_agent_mast","apd_dir_recu_unit","cdb_agent_mast","apd_unit","aps_unit","cdb_agent_mast","apd_dir_recu_unit","apd_unit","cdb_agent_mast","apd_dir_recu_unit","aps_dir_recru","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","aes_acc_ath","ap_nonagt_mast","agt_para_setup","apd_personal","apd_personal_sum","cdb_agent_mast","agt_mvmt_log","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","aes_acc_ath","agt_mfin_valid","aes_acc_ath","ap_nonagt_mast","cdb_agent_extmast","ap_nonagt_mast","cdb_agent_extmast","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_extmast","cdb_agt_ext","ap_nonagt_mast","cdb_agent_extmast","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","agt_mkt_award","agt_para_setup","mkt_award","agt_education","agt_para_setup","agt_prof","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","cdb_agt_ext","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","agt_aux_rec","cdb_agent_submast","agt_pru_cs","agt_pru_cs_cred","agt_mkt_award","agt_para_setup","cdb_agent_mast","cdb_agent_mast","ap_nonagt_mast","aes_acc_ath","aes_acc_ath","ap_nonagt_mast","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agt_ext","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agt_ext","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agt_ext","cdb_agent_extmast","cdb_agent_extmast","cdb_agent_extmast","cdb_agent_extmast","agt_desgn","cdb_agent_mast","agt_desgn","aes_acc_ath","agt_orphan_policy","cdb_agent_mast","cdb_agent_mast","agt_rpt_hier","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","agt_desgn","cdb_agent_extmast","cdb_agent_mast","agt_rpt_hier","cdb_agent_mast","aes_acc_ath","aes_acc_ath","cdb_agent_extmast","cdb_agent_mast","agt_rpt_hier","cdb_agent_mast","aes_acc_ath","agt_rpt_hier","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","agt_rpt_hier","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","agt_orphan_policy","aes_acc_ath","cdb_agent_mast","aes_acc_ath","pol_benf_per_hdr","agt_rpt_hier","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","cdb_agent_mast","aes_acc_ath","cdb_agent_mast","aes_acc_ath","aes_acc_ath","aes_acc_ath","aes_acc_ath","cdb_agent_mast","aes_acc_ath","aes_acc_ath","afn_mth_def","agt_aux_rcd","agt_mfin_hdr","agt_mfin_valid","agt_para_setup","cdb_agent_mast","mvmt_sched_hdr","agt_mkt_award","agt_mvmt_log","agt_rpt_hier","cdb_agent_mast","mkt_award","agt_mkt_award","agt_mvmt_log","agt_rpt_hier","cdb_agent_mast","mkt_award","acs_remark","agt_aux_rec","agt_desgn","agt_para_setup","agt_pfm_rvw","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","cdb_agt_reg","cdb_seat","agt_nmcard_setup","agt_aux_rec","agt_desgn","agt_para_setup","cdb_agent_mast","cdb_agent_submast","agt_nmcard_setup","agt_desgn","agt_education","agt_mkt_award","agt_nmcard_setup","agt_prof","mkt_award","cdb_agent_mast","acs_remark","agt_aux_rec","agt_desgn","agt_para_setup","agt_pfm_rvw","cdb_agent_submast","cdb_agent_mast","agt_prod","cdb_agent_mast","prod_credit","agt_prod","prod_credit","agt_prod","cdb_agent_mast","prod_credit","agt_prod","apd_personal","aps_agent","aps_agent_13","cdb_agent_mast","mvmt_sched_hdr","apd_personal","cdb_agent_mast","apd_personal","cdb_agent_mast","prod_credit","add_easiid_mast","agt_para_setup","apd_psn_nbsub_day","apd_psn_nbsub_mth","cdb_agent_mast","apd_psn_nbsub_mth","cdb_agent_mast","agt_aux_rec","agt_aux_rec","cdb_agent_mast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","apd_case_cnt","apd_personal","aps_agent","aps_agent_13","cdb_agent_mast","cdb_agent_mast","prod_credit","apd_case_cnt","apd_personal","aps_agent","aps_agent_13","cdb_agent_mast","prod_credit","ref_rate_grp","ref_rate_tbl","cdb_agent_mast","agt_rpt_hier","cdb_agent_mast","cdb_agent_mast","aes_acc_ath","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","cdb_agent_mast","aff_scbdisc","cdb_agent_extmast","cdb_agent_mast","prod_credit","cdb_agent_submast","cdb_agent_submast","cdb_agt_ext","aes_acc_ath","aes_acc_ath","agt_aux_rec","cdb_agent_mast","agt_wtd_cycle","aes_acc_ath","cdb_agent_mast","cdb_agent_offi","aes_acc_ath","agt_aux_rec","agt_rpt_hier","ap_nonagt_mast","cdb_agent_extmast","cdb_agent_mast","cdb_agent_submast","cdb_agent_extmast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","efax_mast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","cdb_agent_extmast","agt_aux_rec","cdb_agent_extmast","cdb_agent_mast","aes_acc_ath","ap_nonagt_mast","cdb_agent_mast","agt_rpt_hier","cdb_agent_submast","ap_nonagt_mast","ap_nonagt_mast","mvmt_sched_hdr","cdb_agent_extmast","agt_aux_rec","agt_efax_pool","cdb_agent_extmast","ap_user_role","ap_nonagt_mast","aes_acc_ath","cdb_agent_mast","aes_acc_ath","cdb_agt_ext","cdb_agt_ext","agt_team_cv","cdb_agt_ext","cdb_agt_ext","agt_rpt_hier","agt_orphan_icsr","aes_cpd_hrs"};
 //String[] db2Tables={"register_client"};//CWS_PARAMETER
 String db2RegistClient = "register_client";
 String[] db2Prepare2CreateClients={"7848133","7860825"};
private static Connection  getConnection() throws ClassNotFoundException, SQLException
{
   //Class.forName( "com.informix.jdbc.IfxDriver"  );
	Class.forName( "com.ibm.db2.jcc.DB2Driver" );
   Connection  connection =
   DriverManager.getConnection("jdbc:db2://hkguxdb202:50000/cwsuat","db2hk","abcd12345");
   
   //jdbc:db2://hkguxdb202:50000/cwsuat
   //DriverManager.getConnection("jdbc:informix-sqli://hkguxifx02:8912/prulife:INFORMIXSERVER=prupu5;DB_LOCALE=zh_hk.big5-hkscs;CLIENT_LOCALE=zh_hk.big5-hkscs;NEWCODESET=Big5_HKSCS,big5-hkscs,1375;NEWLOCALE=zh_hk,zh_HK;GL_USEGLU=1;IFX_USE_STRENC=true;IFX_SOC_TIMEOUT=600000","xpag1","20Pachk10");
   //DriverManager.getConnection("jdbc:informix-sqli://hkgdxifx02:9032/prulife:INFORMIXSERVER=prupd1;DB_LOCALE=zh_hk.big5-hkscs;CLIENT_LOCALE=zh_hk.big5-hkscs;NEWCODESET=Big5_HKSCS,big5-hkscs,1375;NEWLOCALE=zh_hk,zh_HK;GL_USEGLU=1;IFX_USE_STRENC=true;IFX_SOC_TIMEOUT=600000","xpag1","20Pachk10");
   //<JDBC JDBCDriver="com.informix.jdbc.IfxDriver" JDBC_URL="jdbc:informix-sqli://hkguxifx02:8912/prulife:INFORMIXSERVER=prupu5;DB_LOCALE=zh_hk.big5-hkscs;CLIENT_LOCALE=zh_hk.big5-hkscs;NEWCODESET=Big5_HKSCS,big5-hkscs,1375;NEWLOCALE=zh_hk,zh_HK;GL_USEGLU=1;IFX_USE_STRENC=true;IFX_SOC_TIMEOUT=600000" userid="xpag1" password="20Pachk10"/>
   //DriverManager.getConnection("jdbc:informix-sqli://hkgdxifx02:9044/prulife:INFORMIXSERVER=prupd3;DB_LOCALE=zh_hk.big5-hkscs;CLIENT_LOCALE=zh_hk.big5-hkscs;NEWCODESET=Big5_HKSCS,big5-hkscs,1375;NEWLOCALE=zh_hk,zh_HK;GL_USEGLU=1;IFX_USE_STRENC=true;IFX_SOC_TIMEOUT=600000","xpag1","20Pachk10");
   //DriverManager.getConnection("jdbc:informix-sqli://hkgdxifx02:9032/prulife:INFORMIXSERVER=prupd1;DB_LOCALE=zh_hk.big5-hkscs;CLIENT_LOCALE=zh_hk.big5-hkscs;NEWCODESET=Big5_HKSCS,big5-hkscs,1375;NEWLOCALE=zh_hk,zh_HK;GL_USEGLU=1;IFX_USE_STRENC=true;IFX_SOC_TIMEOUT=600000","xpag1","20Pachk10");
   System. out .println( "Connection obtained " );
   return connection;
}
private static final String toISODateString(java.util.Date date) {
    if (date == null)
      return null;
    else
      return new SimpleDateFormat("yyyy-MM-dd").format(date);
  }
//rename table acs_agt_bal to acs_agt_bal_uat;

public void SelectTables(){
  List<String[]> errorList = new ArrayList<String[]>();
  List<String[]> out = new ArrayList<String[]>();
  int errorSize=0;
  try {
    conn= getConnection ();
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (SQLException e) {
    e.printStackTrace();
  }
    CallableStatement stmt = null;
    String sql = null;
    ResultSet rs = null;
    String[] row = new String[2];
 
    sql = "select * from "+db2RegistClient + "";
    //insert into register_client (CLIENT_CD) values('01078798');
    System.out.println("testing sql="+sql+";"); 
    try {
    stmt = conn.prepareCall(sql);
    rs = stmt.executeQuery();
    
    while (rs.next()) {
      //size++;
      System.out.println(" size="+rs.getLong(1));
      row = new String[2];
      row[0] = ""+rs.getString(1);
      row[1] = db2RegistClient;
      out.add(row);
    }

    
    } catch (SQLException e) {
      row = new String[2];
      row[0] = db2RegistClient;
      row[1] = ""+e.getErrorCode();
      
      errorList.add(row);
      System.out.println("errorcode = "+e.getErrorCode());
      //e.printStackTrace();
      errorSize++;
    }
    //System.out.println("sql="+sql+";                 size="+size);
 
 for (String[] r:out){
	System.out.print(""+r[0]+")");
   System.out.println("["+r[1]+"]");
 }
   out.addAll(errorList);
   CsvWritor.generateFile(out,null);  
    System.out.println("errorList="+errorList );
}


@Test
public void deleteInsertSelectTables(){
	delete();
	InsertSelectTables(Arrays.asList(db2Prepare2CreateClients));
    SelectTables();
}
public void delete() {
	 try {
		    conn= getConnection ();
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		    CallableStatement stmt = null;
		    String sql = null;
		    ResultSet rs = null; 
		    
		    sql = "delete from register_client";
		    System.out.println("deleting sql="+sql+"");
		    boolean done=false;
		    try {
		        stmt = conn.prepareCall(sql);
		        done= stmt.execute();
		      }catch (SQLException e) {
		        e.printStackTrace();
		      }
		    System.out.println(""+sql+"+"+done); 
		    
	
}
public void InsertSelectTables(List<String> db2Prepare2CreateClients){
  Set<String> tempClients = new LinkedHashSet<String>( db2Prepare2CreateClients);
  //String[] clients = tempClients.toArray( new String[tempClients.size()] );
  //System. out .println( "result:"+Arrays.asList(clients ));
 
  List<String[]> out = new ArrayList<String[]>();
  try {
    conn= getConnection ();
  } catch (ClassNotFoundException e) {
    e.printStackTrace();
  } catch (SQLException e) {
    e.printStackTrace();
  }
    String sql = null;
    
    
    Statement stmtInsert;
    int updatedNo=0;
    try {
    for(String client:tempClients) {
    
    sql = "insert into register_client (CLIENT_CD) values('"+client+"')";
    stmtInsert =  conn.createStatement();
    System.out.println("running sql="+sql+"");
    stmtInsert.executeUpdate(sql);
    updatedNo++;
    }

    System.out.println("ran sql="+sql+" for "+updatedNo+"times");
    
    
    } catch (SQLException e) {
      System.out.println("errorcode = "+e.getErrorCode());
      e.printStackTrace();
    }
    
    CsvWritor.generateFile(out,null);  
    
    
}

}
