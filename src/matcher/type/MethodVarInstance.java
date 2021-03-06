package matcher.type;

public class MethodVarInstance implements IMatchable<MethodVarInstance> {
	MethodVarInstance(MethodInstance method, boolean isArg, int index, int lvIndex, int asmIndex,
			ClassInstance type, int startInsn, int endInsn,
			String origName, boolean nameObfuscated) {
		this.method = method;
		this.isArg = isArg;
		this.index = index;
		this.lvIndex = lvIndex;
		this.asmIndex = asmIndex;
		this.type = type;
		this.startInsn = startInsn;
		this.endInsn = endInsn;
		this.origName = origName;
		this.nameObfuscated = nameObfuscated;
	}

	public MethodInstance getMethod() {
		return method;
	}

	public boolean isArg() {
		return isArg;
	}

	public int getIndex() {
		return index;
	}

	public int getLvIndex() {
		return lvIndex;
	}

	public int getAsmIndex() {
		return asmIndex;
	}

	public ClassInstance getType() {
		return type;
	}

	public int getStartInsn() {
		return startInsn;
	}

	public int getEndInsn() {
		return endInsn;
	}

	@Override
	public String getId() {
		return Integer.toString(index);
	}

	@Override
	public String getName() {
		return origName;
	}

	@Override
	public ClassEnv getEnv() {
		return method.getEnv();
	}

	@Override
	public boolean isNameObfuscated() {
		return nameObfuscated;
	}

	@Override
	public String getTmpName(boolean unmatched) {
		String ret;

		if (!unmatched && matchedInstance != null && (ret = matchedInstance.getTmpName(true)) != null) {
			return ret;
		}

		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	@Override
	public int getUid() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getUidString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMappedName() {
		if (mappedName != null) {
			return mappedName;
		} else if (matchedInstance != null && matchedInstance.mappedName != null) {
			return matchedInstance.mappedName;
		} else {
			return null;
		}
	}

	public void setMappedName(String mappedName) {
		this.mappedName = mappedName;
	}

	@Override
	public MethodVarInstance getMatch() {
		return matchedInstance;
	}

	public void setMatch(MethodVarInstance match) {
		assert match == null || method == match.method.getMatch();

		this.matchedInstance = match;
	}

	@Override
	public String toString() {
		return method.toString()+":"+index;
	}

	final MethodInstance method;
	final boolean isArg;
	final int index;
	final int lvIndex;
	final int asmIndex;
	final ClassInstance type;
	final int startInsn; // inclusive
	final int endInsn; // exclusive
	final String origName;
	final boolean nameObfuscated;

	private String tmpName;

	private String mappedName;
	private MethodVarInstance matchedInstance;
}
