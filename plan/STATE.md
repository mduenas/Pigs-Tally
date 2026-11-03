---
stage: prototype
reviewed_at: 2026-11-02
milestone:
  title: Android targetSdk 36 release
  target: 2026-11-30
  done_when: "1.0.1 (vc 4, targetSdk 36) is published to production on Google Play"
---

## Next steps

- [x] Commit compile/target SDK 36 + versionCode 3 (DUE-63, PR #1)
- [x] Bump to 1.0.1 (vc 4) and build release AAB from committed code (DUE-14)
- [ ] Mark Testing on Play internal track (vc 4)
- [ ] After Mark's Ready for Release: promote vc 4 to production as a draft; Mark publishes
- [ ] Tag `android-v1.0.1-4` after publish

## Notes

- Play tracks (Nov 2, 2026): production 1.0 (vc 2, target 35, compliant for an existing app), beta 1.0 (vc 2), alpha empty, internal 1.0.1 (vc 4, target 36).
- Target 36 is required for the next update (Play policy since Aug 31, 2026), not to clear a current violation.
- Seeded by adopt-state on 2026-07-28; updated Nov 2, 2026 for DUE-14.
