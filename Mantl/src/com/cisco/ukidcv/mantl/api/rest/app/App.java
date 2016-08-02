
package com.cisco.ukidcv.mantl.api.rest.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Java implementation of the Marathon JSON API.
 * <p>
 * Please see the Marathon documentation for detail on implementation.
 *
 * @author Matt Day
 *
 */
public class App {

	private String id;
	private String cmd;
	private List<String> args = new ArrayList<>();
	private Env env;
	private int instances;
	private double cpus;
	private int mem;
	private int disk;
	private String executor;
	private List<List<String>> constraints = new ArrayList<>();
	private List<String> uris = new ArrayList<>();
	private List<Integer> ports = new ArrayList<>();
	private boolean requirePorts;
	private int backoffSeconds;
	private double backoffFactor;
	private int maxLaunchDelaySeconds;
	private Container container;
	private List<HealthCheck> healthChecks = new ArrayList<>();
	private UpgradeStrategy upgradeStrategy;
	private Labels_ labels;
	private String version;
	private VersionInfo versionInfo;
	private int tasksStaged;
	private int tasksRunning;
	private int tasksHealthy;
	private int tasksUnhealthy;
	private List<Deployment> deployments = new ArrayList<>();

	/**
	 *
	 * @return The id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 *
	 * @param id
	 *            The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return The cmd
	 */
	public String getCmd() {
		return this.cmd;
	}

	/**
	 *
	 * @param cmd
	 *            The cmd
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 *
	 * @return The args
	 */
	public List<String> getArgs() {
		return this.args;
	}

	/**
	 *
	 * @param args
	 *            The args
	 */
	public void setArgs(List<String> args) {
		this.args = args;
	}

	/**
	 *
	 * @return The env
	 */
	public Env getEnv() {
		return this.env;
	}

	/**
	 *
	 * @param env
	 *            The env
	 */
	public void setEnv(Env env) {
		this.env = env;
	}

	/**
	 *
	 * @return The instances
	 */
	public int getInstances() {
		return this.instances;
	}

	/**
	 *
	 * @param instances
	 *            The instances
	 */
	public void setInstances(int instances) {
		this.instances = instances;
	}

	/**
	 *
	 * @return The cpus
	 */
	public double getCpus() {
		return this.cpus;
	}

	/**
	 *
	 * @param cpus
	 *            The cpus
	 */
	public void setCpus(double cpus) {
		this.cpus = cpus;
	}

	/**
	 *
	 * @return The mem
	 */
	public int getMem() {
		return this.mem;
	}

	/**
	 *
	 * @param mem
	 *            The mem
	 */
	public void setMem(int mem) {
		this.mem = mem;
	}

	/**
	 *
	 * @return The disk
	 */
	public int getDisk() {
		return this.disk;
	}

	/**
	 *
	 * @param disk
	 *            The disk
	 */
	public void setDisk(int disk) {
		this.disk = disk;
	}

	/**
	 *
	 * @return The executor
	 */
	public String getExecutor() {
		return this.executor;
	}

	/**
	 *
	 * @param executor
	 *            The executor
	 */
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	/**
	 *
	 * @return The constraints
	 */
	public List<List<String>> getConstraints() {
		return this.constraints;
	}

	/**
	 *
	 * @param constraints
	 *            The constraints
	 */
	public void setConstraints(List<List<String>> constraints) {
		this.constraints = constraints;
	}

	/**
	 *
	 * @return The uris
	 */
	public List<String> getUris() {
		return this.uris;
	}

	/**
	 *
	 * @param uris
	 *            The uris
	 */
	public void setUris(List<String> uris) {
		this.uris = uris;
	}

	/**
	 *
	 * @return The ports
	 */
	public List<Integer> getPorts() {
		return this.ports;
	}

	/**
	 *
	 * @param ports
	 *            The ports
	 */
	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	/**
	 *
	 * @return The requirePorts
	 */
	public boolean isRequirePorts() {
		return this.requirePorts;
	}

	/**
	 *
	 * @param requirePorts
	 *            The requirePorts
	 */
	public void setRequirePorts(boolean requirePorts) {
		this.requirePorts = requirePorts;
	}

	/**
	 *
	 * @return The backoffSeconds
	 */
	public int getBackoffSeconds() {
		return this.backoffSeconds;
	}

	/**
	 *
	 * @param backoffSeconds
	 *            The backoffSeconds
	 */
	public void setBackoffSeconds(int backoffSeconds) {
		this.backoffSeconds = backoffSeconds;
	}

	/**
	 *
	 * @return The backoffFactor
	 */
	public double getBackoffFactor() {
		return this.backoffFactor;
	}

	/**
	 *
	 * @param backoffFactor
	 *            The backoffFactor
	 */
	public void setBackoffFactor(double backoffFactor) {
		this.backoffFactor = backoffFactor;
	}

	/**
	 *
	 * @return The maxLaunchDelaySeconds
	 */
	public int getMaxLaunchDelaySeconds() {
		return this.maxLaunchDelaySeconds;
	}

	/**
	 *
	 * @param maxLaunchDelaySeconds
	 *            The maxLaunchDelaySeconds
	 */
	public void setMaxLaunchDelaySeconds(int maxLaunchDelaySeconds) {
		this.maxLaunchDelaySeconds = maxLaunchDelaySeconds;
	}

	/**
	 *
	 * @return The container
	 */
	public Container getContainer() {
		return this.container;
	}

	/**
	 *
	 * @param container
	 *            The container
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 *
	 * @return The healthChecks
	 */
	public List<HealthCheck> getHealthChecks() {
		return this.healthChecks;
	}

	/**
	 *
	 * @param healthChecks
	 *            The healthChecks
	 */
	public void setHealthChecks(List<HealthCheck> healthChecks) {
		this.healthChecks = healthChecks;
	}

	/**
	 *
	 * @return The upgradeStrategy
	 */
	public UpgradeStrategy getUpgradeStrategy() {
		return this.upgradeStrategy;
	}

	/**
	 *
	 * @param upgradeStrategy
	 *            The upgradeStrategy
	 */
	public void setUpgradeStrategy(UpgradeStrategy upgradeStrategy) {
		this.upgradeStrategy = upgradeStrategy;
	}

	/**
	 *
	 * @return The labels
	 */
	public Labels_ getLabels() {
		return this.labels;
	}

	/**
	 *
	 * @param labels
	 *            The labels
	 */
	public void setLabels(Labels_ labels) {
		this.labels = labels;
	}

	/**
	 *
	 * @return The version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 *
	 * @param version
	 *            The version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 *
	 * @return The versionInfo
	 */
	public VersionInfo getVersionInfo() {
		return this.versionInfo;
	}

	/**
	 *
	 * @param versionInfo
	 *            The versionInfo
	 */
	public void setVersionInfo(VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}

	/**
	 *
	 * @return The tasksStaged
	 */
	public int getTasksStaged() {
		return this.tasksStaged;
	}

	/**
	 *
	 * @param tasksStaged
	 *            The tasksStaged
	 */
	public void setTasksStaged(int tasksStaged) {
		this.tasksStaged = tasksStaged;
	}

	/**
	 *
	 * @return The tasksRunning
	 */
	public int getTasksRunning() {
		return this.tasksRunning;
	}

	/**
	 *
	 * @param tasksRunning
	 *            The tasksRunning
	 */
	public void setTasksRunning(int tasksRunning) {
		this.tasksRunning = tasksRunning;
	}

	/**
	 *
	 * @return The tasksHealthy
	 */
	public int getTasksHealthy() {
		return this.tasksHealthy;
	}

	/**
	 *
	 * @param tasksHealthy
	 *            The tasksHealthy
	 */
	public void setTasksHealthy(int tasksHealthy) {
		this.tasksHealthy = tasksHealthy;
	}

	/**
	 *
	 * @return The tasksUnhealthy
	 */
	public int getTasksUnhealthy() {
		return this.tasksUnhealthy;
	}

	/**
	 *
	 * @param tasksUnhealthy
	 *            The tasksUnhealthy
	 */
	public void setTasksUnhealthy(int tasksUnhealthy) {
		this.tasksUnhealthy = tasksUnhealthy;
	}

	/**
	 *
	 * @return The deployments
	 */
	public List<Deployment> getDeployments() {
		return this.deployments;
	}

	/**
	 *
	 * @param deployments
	 *            The deployments
	 */
	public void setDeployments(List<Deployment> deployments) {
		this.deployments = deployments;
	}

}
